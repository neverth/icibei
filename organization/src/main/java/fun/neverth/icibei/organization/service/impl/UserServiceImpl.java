package fun.neverth.icibei.organization.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.UserMapper;
import fun.neverth.icibei.organization.entity.form.UserForm;
import fun.neverth.icibei.organization.entity.param.UserQueryParam;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.entity.po.UserInfo;
import fun.neverth.icibei.organization.entity.vo.UserVO;
import fun.neverth.icibei.organization.service.UserInfoService;
import fun.neverth.icibei.organization.service.UserRoleService;
import fun.neverth.icibei.organization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:27
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserInfoService userInfoService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public boolean add(UserForm userForm){
        User user = userForm.toPO(User.class);
        if (add(user)){
            user = this.getByUniqueId(userForm.getUsername()).toPO(User.class);
        }
        Assert.notNull(user, "用户插入失败");

        UserInfo userInfo = new UserInfo();
        userInfo.setAccountId(userForm.getUsername());
        userInfo.setUserId(user.getId());

        return userInfoService.add(userInfo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public boolean add(User user) {
        Assert.notBlank(user.getPassword(), "用户密码不能为空");
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        // 插入用户，再插入用户角色
        boolean inserts = this.save(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return inserts;
    }

    @Override
    public boolean delete(String id) {
        this.removeById(id);
        return userRoleService.removeByUserId(id);
    }

    @Override
    public boolean update(User user) {
        if (StringUtils.isNotBlank(user.getPassword()))
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        boolean isSuccess = this.updateById(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return isSuccess;
    }

    @Override
    public UserVO get(String id) {
        User user = this.getById(id);
        if (user != null) {
            user.setRoleIds(userRoleService.queryByUserId(id));
        }
        return new UserVO(user);
    }

    @Override
    public UserVO getByUniqueId(String uniqueId) {
        User user = this.getOne(new QueryWrapper<User>()
                .eq("username", uniqueId)
                .or()
                .eq("mobile", uniqueId));
        if (user != null) {
            user.setRoleIds(userRoleService.queryByUserId(user.getId()));
            return new UserVO(user);
        }
        return null;
    }

    @Override
    public IPage<UserVO> query(Page page, UserQueryParam userQueryParam) {
        QueryWrapper<User> queryWrapper = userQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getName()), "name", userQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getUsername()), "username", userQueryParam.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getMobile()), "mobile", userQueryParam.getMobile());
        // 转换成VO
        IPage<User> iPageUser = this.page(page, queryWrapper);
        return iPageUser.convert(UserVO::new);
    }

}
