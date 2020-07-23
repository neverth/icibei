package fun.neverth.icibei.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.sysadmin.organization.dao.PositionMapper;
import fun.neverth.icibei.sysadmin.organization.entity.param.PositionQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.Position;
import fun.neverth.icibei.sysadmin.organization.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:58
 */
@Service
@Slf4j
public class PositionServiceImpl
        extends ServiceImpl<PositionMapper, Position>
        implements PositionService {
    @Override
    public boolean add(Position position) {
        return this.save(position);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#position.id")
    public boolean update(Position position) {
        return this.updateById(position);
    }

    @Override
    @Cached(name = "position::", key = "#id", cacheType = CacheType.BOTH)
    public Position get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = positionQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(positionQueryParam.getName()), "name", positionQueryParam.getName());
        return this.list(queryWrapper);
    }
}
