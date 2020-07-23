package fun.neverth.icibei.sysadmin.organization.service;

import fun.neverth.icibei.sysadmin.organization.entity.param.GatewayRouteQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.GatewayRoute;
import fun.neverth.icibei.sysadmin.organization.entity.vo.GatewayRouteVO;

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
