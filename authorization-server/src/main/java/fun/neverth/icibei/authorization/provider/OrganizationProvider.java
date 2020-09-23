package fun.neverth.icibei.authorization.provider;

import fun.neverth.icibei.authorization.entity.IRole;
import fun.neverth.icibei.authorization.entity.IUser;
import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 15:53
 */
@FeignClient(name = "icibei-organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user/{uniqueId}")
    Result<IUser> getUserByUniqueId(@PathVariable("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<IRole>> queryRolesByUserId(@PathVariable("userId") String userId);
}
