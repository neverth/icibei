package fun.neverth.icibei.gateway.web.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * @author NeverTh
 * @date 12:03 2020/10/4
 */
public interface GatewayRouteService {

    Collection<RouteDefinition> getRouteDefinitions();
}
