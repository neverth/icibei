package fun.neverth.icibei.gateway.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.neverth.icibei.gateway.web.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author NeverTh
 * @date 10:13 2020/10/4
 */
@Repository
@Mapper
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}
