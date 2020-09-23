package fun.neverth.icibei.authorization.service.impl;

import fun.neverth.icibei.authorization.entity.IRole;
import fun.neverth.icibei.authorization.provider.OrganizationProvider;
import fun.neverth.icibei.authorization.service.IRoleService;
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
public class IRoleServiceImpl implements IRoleService {

    @Resource
    private OrganizationProvider organizationProvider;

    @Override
    public Set<IRole> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
