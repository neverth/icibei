package fun.neverth.icibei.authorization.service;

import fun.neverth.icibei.authorization.entity.IUser;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:04
 */

public interface IUserService {

    IUser getByUniqueId(String uniqueId);
}
