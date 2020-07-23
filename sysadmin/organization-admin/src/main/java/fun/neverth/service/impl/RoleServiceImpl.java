package fun.neverth.service.impl;

import fun.neverth.entity.po.Role;
import fun.neverth.entity.po.User;
import fun.neverth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:57
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Override
    public Role get(String id) {
        return null;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
