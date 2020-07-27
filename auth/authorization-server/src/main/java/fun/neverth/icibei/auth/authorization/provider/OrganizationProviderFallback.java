package fun.neverth.icibei.auth.authorization.provider;

import fun.neverth.icibei.auth.authorization.entity.Role;
import fun.neverth.icibei.auth.authorization.entity.User;
import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * OrganizationProvider服务降级
 *
 * @author NeverTh
 * @date 2020/7/24 16:00
 */
@Component
public class OrganizationProviderFallback implements OrganizationProvider{
    @Override
    public Result<User> getUserByUniqueId(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<Role>());
    }
}
