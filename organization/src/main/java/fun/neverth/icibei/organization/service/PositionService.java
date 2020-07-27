package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.po.Position;
import fun.neverth.icibei.organization.entity.param.PositionQueryParam;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface PositionService {

    Position get(String id);

    boolean add(Position position);

    List<Position> query(PositionQueryParam positionQueryParam);

    boolean update(Position position);

    boolean delete(String id);
}
