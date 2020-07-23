package fun.neverth.service;

import fun.neverth.entity.po.Position;
import fun.neverth.entity.po.User;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface PositionService {

    Position get(String id);

    boolean add(User user);

    boolean update(User user);

    boolean delete(String id);
}
