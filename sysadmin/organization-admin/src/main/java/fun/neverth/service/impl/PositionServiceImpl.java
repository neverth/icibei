package fun.neverth.service.impl;

import fun.neverth.entity.po.Position;
import fun.neverth.entity.po.User;
import fun.neverth.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:58
 */
@Service
@Slf4j
public class PositionServiceImpl implements PositionService {
    @Override
    public Position get(String id) {
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
