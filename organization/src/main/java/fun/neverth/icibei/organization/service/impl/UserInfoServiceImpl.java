package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import fun.neverth.icibei.organization.dao.UserInfoMapper;
import fun.neverth.icibei.organization.entity.po.Role;
import fun.neverth.icibei.organization.entity.po.UserInfo;
import fun.neverth.icibei.organization.entity.po.UserRole;
import fun.neverth.icibei.organization.entity.vo.UserInfoVO;
import fun.neverth.icibei.organization.service.RoleService;
import fun.neverth.icibei.organization.service.UserInfoService;
import fun.neverth.icibei.organization.service.UserRoleService;
import fun.neverth.icibei.organization.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    final String accessKey = "KS1_Wu6paDRLTcv5KYnuu-F_a8l-dzNKEsE-NzzQ";

    final String secretKey = "5xzwds96UAjZEtmCKeaOID7GbFToySajpJ7gvpBl";

    final String bucket = "icibei";

    final String CDN_URL = "http://cdn.neverth.fun";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    /**
     * 根据userId取最近的一条userInfo
     */
    @Override
    public UserInfo getByUserId(String userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 1");
        return this.getOne(queryWrapper);
    }

    @Override
    public UserInfoVO getVoByUserId(String userId) {
        final Set<String> roleIds = userRoleService.queryByUserId(userId);
        Set<String> roles = new HashSet<>();
        for (String roleId : roleIds) {
            roles.add(roleService.get(roleId).getCode());
        }

        final UserInfo userInfo = getByUserId(userId);

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        userInfoVO.setRoles(roles);
        return userInfoVO;
    }

    @Override
    public boolean add(UserInfo userInfo) {
        return this.save(userInfo);
    }

    /**
     * allowNull 是否允许更新字段为空
     */
    @Override
    public boolean update(String userId, UserInfo userInfo, boolean allowNull) {
        UserInfo oldUserInfo = this.getByUserId(userId);
        // 暂时只能修改这三个
        if (allowNull) {
            oldUserInfo.setNickName(userInfo.getNickName());
            oldUserInfo.setSignature(userInfo.getSignature());
            oldUserInfo.setAvatar(userInfo.getAvatar());
        } else {
            if (StringUtils.isNotBlank(userInfo.getNickName())){
                oldUserInfo.setNickName(userInfo.getNickName());
            }
            if (StringUtils.isNotBlank(userInfo.getSignature())){
                oldUserInfo.setSignature(userInfo.getSignature());
            }
            if (StringUtils.isNotBlank(userInfo.getAvatar())){
                oldUserInfo.setAvatar(userInfo.getAvatar());
            }
        }
        oldUserInfo.setId(null);
        return this.add(oldUserInfo);
    }

    @Override
    public boolean uploadAvatar(String userId, MultipartFile file) {
        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            // 使用hash作为文件名
            Response response = uploadManager.put(file.getBytes(), null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            UserInfo userInfo = new UserInfo();
            userInfo.setAvatar(CDN_URL + "/" + putRet.hash);
            return this.update(userId, userInfo, false);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
