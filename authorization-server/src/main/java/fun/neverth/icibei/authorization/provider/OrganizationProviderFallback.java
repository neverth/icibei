package fun.neverth.icibei.authorization.provider;

import fun.neverth.icibei.authorization.entity.IRole;
import fun.neverth.icibei.authorization.entity.IUser;
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
    public Result<IUser> getUserByUniqueId(String uniqueId) {
        return Result.success(new IUser());
    }

    @Override
    public Result<Set<IRole>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<IRole>());
    }
}
