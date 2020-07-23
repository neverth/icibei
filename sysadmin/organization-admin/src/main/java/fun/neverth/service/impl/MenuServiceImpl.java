package fun.neverth.service.impl;

import fun.neverth.entity.po.Menu;
import fun.neverth.entity.po.User;
import fun.neverth.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:58
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public Menu get(String id) {
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
