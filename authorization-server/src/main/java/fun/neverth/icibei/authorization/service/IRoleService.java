package fun.neverth.icibei.authorization.service;

import fun.neverth.icibei.authorization.entity.IRole;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:06
 */
public interface IRoleService {
    Set<IRole> queryUserRolesByUserId(String userId);
}
