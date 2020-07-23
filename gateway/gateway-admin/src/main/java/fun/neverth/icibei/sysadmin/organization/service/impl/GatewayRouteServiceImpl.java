package fun.neverth.icibei.sysadmin.organization.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.neverth.icibei.sysadmin.organization.config.RabbitConfig;
import fun.neverth.icibei.sysadmin.organization.dao.GatewayRouteMapper;
import fun.neverth.icibei.sysadmin.organization.entity.param.GatewayRouteQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.GatewayRoute;
import fun.neverth.icibei.sysadmin.organization.entity.vo.GatewayRouteVO;
import fun.neverth.icibei.sysadmin.organization.events.RabbitEventSender;
import fun.neverth.icibei.sysadmin.organization.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:28
 */
@Service
@Slf4j
public class GatewayRouteServiceImpl
        extends ServiceImpl<GatewayRouteMapper, GatewayRoute>
        implements GatewayRouteService {

    private static final String GATEWAY_ROUTES = "icibei_gateway_routes::";

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Resource
    private RabbitEventSender rabbitEventSender;

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.save(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        rabbitEventSender.send(RabbitConfig.ROUTING_KEY, routeDefinition);
        return isSuccess;
    }

    @Override
    public boolean delete(String id) {
        GatewayRoute gatewayRoute = this.getById(id);
        gatewayRouteCache.remove(gatewayRoute.getRouteId());
        rabbitEventSender.send(
                RabbitConfig.ROUTING_KEY,
                gatewayRouteToRouteDefinition(gatewayRoute)
        );
        return this.delete(id);
    }

    @Override
    public boolean update(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.updateById(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(
                gatewayRoute.getRouteId(),
                routeDefinition
        );
        rabbitEventSender.send(RabbitConfig.ROUTING_KEY, routeDefinition);
        return isSuccess;
    }

    @Override
    public GatewayRoute get(String id) {
        return this.getById(id);
    }

    /**
     * \@PostConstruct 可能是由于jetcache的原因，会出现循环依赖的错误
     */
    @Override
    public boolean overLoadToCache() {
        List<GatewayRoute> gatewayRoutes = this.list(new QueryWrapper<>());
        gatewayRoutes.forEach(gatewayRoute ->
                gatewayRouteCache.put(
                        gatewayRoute.getRouteId(),
                        gatewayRouteToRouteDefinition(gatewayRoute)
                )
        );
        log.info("将路由put入全局缓存成功");
        return true;
    }

    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(
                    objectMapper.readValue(
                            gatewayRoute.getFilters(),
                            new TypeReference<List<FilterDefinition>>() {
                            }
                    )
            );
            routeDefinition.setPredicates(
                    objectMapper.readValue(
                            gatewayRoute.getPredicates(),
                            new TypeReference<List<PredicateDefinition>>() {
                            }
                    )
            );
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }

    @Override
    public List<GatewayRouteVO> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        return null;
    }
}
