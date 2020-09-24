package fun.neverth.icibei.gateway.web.service;

import fun.neverth.icibei.gateway.web.entity.param.GatewayRouteQueryParam;
import fun.neverth.icibei.gateway.web.entity.vo.GatewayRouteVO;
import fun.neverth.icibei.gateway.web.entity.po.GatewayRoute;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:26
 */
public interface GatewayRouteService {

    boolean add(GatewayRoute gatewayRoute);

    boolean delete(String id);

    boolean update(GatewayRoute gatewayRoute);

    GatewayRoute get(String id);

    boolean overLoadToCache();

    List<GatewayRouteVO> query(GatewayRouteQueryParam gatewayRouteQueryParam);

}
