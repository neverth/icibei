package fun.neverth.icibei.auth.authorization.service.impl;

import fun.neverth.icibei.auth.authorization.entity.User;
import fun.neverth.icibei.auth.authorization.provider.OrganizationProvider;
import fun.neverth.icibei.auth.authorization.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private OrganizationProvider organizationProvider;

    @Cacheable(value = "#id")
    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
