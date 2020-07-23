package fun.neverth.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.dao.UserMapper;
import fun.neverth.entity.param.UserQueryParam;
import fun.neverth.entity.po.User;
import fun.neverth.entity.vo.UserVO;
import fun.neverth.service.UserRoleService;
import fun.neverth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    UserRoleService userRoleService;

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Cached(name = "user::", key = "#id", cacheType = CacheType.BOTH)
    public UserVO get(String id) {
        User user = this.getById(id);
        if (Objects.isNull(user)){
        }
        user.setRoleIds(userRoleService.queryByUserId(id));
        return new UserVO(user);
    }

    @Override
    @Cached(name = "user::", key = "#uniqueId", cacheType = CacheType.BOTH)
    public User getByUniqueId(String uniqueId) {
        User user = this.getOne(new QueryWrapper<User>()
                .eq("username", uniqueId)
                .or()
                .eq("mobile", uniqueId)
        );
        if (Objects.isNull(user)){

        }
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return user;
    }

    @Override
    public boolean add(User user) {
        if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        boolean insert = this.save(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return insert;
    }

    @Override
    public IPage<UserVO> query(Page<User> page, UserQueryParam userQueryParam) {
        return null;
    }

    @Override
    public boolean update(User user) {
        if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        boolean update = this.updateById(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return update;
    }

    @Override
    public boolean delete(String id) {
        this.removeById(id);
        return userRoleService.removeByUserId(id);
    }
}
