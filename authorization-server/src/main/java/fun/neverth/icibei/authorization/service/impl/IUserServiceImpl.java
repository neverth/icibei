package fun.neverth.icibei.authorization.service.impl;

import fun.neverth.icibei.authorization.entity.IUser;
import fun.neverth.icibei.authorization.provider.OrganizationProvider;
import fun.neverth.icibei.authorization.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:05
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private OrganizationProvider organizationProvider;

    @Override
    public IUser getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }

    @Override
    public IUser getById(String userId) {
        return organizationProvider.getUserById(userId).getData();
    }
}
