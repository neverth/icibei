package fun.neverth.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 18:23
 */
public interface RouteService {

    boolean saveRouteDefinition(RouteDefinition routeDefinition);

    boolean deleteRouteDefinition(String routeId);

    boolean updateRouteDefinition(RouteDefinition routeDefinition);

    Collection<RouteDefinition> getRouteDefinitions();

}
