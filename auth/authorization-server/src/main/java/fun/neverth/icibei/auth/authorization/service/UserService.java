package fun.neverth.icibei.auth.authorization.service;

import fun.neverth.icibei.auth.authorization.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/24 16:04
 */

public interface UserService {

    User getByUniqueId(String uniqueId);
}
