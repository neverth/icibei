package fun.neverth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.neverth.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:25
 */
@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}
