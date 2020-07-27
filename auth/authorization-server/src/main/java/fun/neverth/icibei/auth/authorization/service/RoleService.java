package fun.neverth.icibei.auth.authorization.service;

import fun.neverth.icibei.auth.authorization.entity.Role;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:06
 */
public interface RoleService {
    Set<Role> queryUserRolesByUserId(String userId);
}
