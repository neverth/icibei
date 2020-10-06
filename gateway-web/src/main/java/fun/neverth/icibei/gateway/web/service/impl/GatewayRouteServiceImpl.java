package fun.neverth.icibei.gateway.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.neverth.icibei.gateway.web.dao.GatewayRouteMapper;
import fun.neverth.icibei.gateway.web.entity.po.GatewayRoute;
import fun.neverth.icibei.gateway.web.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NeverTh
 * @date 12:04 2020/10/4
 */
@Service
@Slf4j
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements GatewayRouteService {

    private final Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    /**
     * 初始化路由
     */
    @PostConstruct
    private void initRouteDefinition(){
        List<GatewayRoute> allRoutes = this.list();
        if (CollectionUtils.isEmpty(allRoutes)){
            log.warn("没有路由信息");
            return;
        }
        for (GatewayRoute gateWayRoute : allRoutes) {
            routeDefinitionMaps.put(gateWayRoute.getRouteId(), gatewayRouteToRouteDefinition(gateWayRoute));
        }
        log.info("共初始化路由信息：{}个。", routeDefinitionMaps.size());
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMaps.values();
    }

    /**
     * 将数据库中json对象转换为网关需要的RouteDefinition对象
     */
    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }
}
