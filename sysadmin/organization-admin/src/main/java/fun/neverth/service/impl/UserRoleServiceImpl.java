package fun.neverth.service.impl;

import fun.neverth.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 20:05
 */
@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public boolean saveBatch(String userId, Set<String> roleIds) {
        return false;
    }

    @Override
    public boolean removeByUserId(String userId) {
        return false;
    }

    @Override
    public Set<String> queryByUserId(String userId) {
        return null;
    }
}
