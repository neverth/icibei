package fun.neverth.icibei.auth.authorization.service.impl;

import fun.neverth.icibei.auth.authorization.entity.Role;
import fun.neverth.icibei.auth.authorization.provider.OrganizationProvider;
import fun.neverth.icibei.auth.authorization.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:07
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
