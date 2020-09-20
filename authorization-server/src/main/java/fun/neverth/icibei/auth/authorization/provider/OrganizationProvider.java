package fun.neverth.icibei.auth.authorization.provider;

import fun.neverth.icibei.auth.authorization.entity.Role;
import fun.neverth.icibei.auth.authorization.entity.User;
import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    Result<User> getUserByUniqueId(@PathVariable("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);
}
