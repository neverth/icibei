package fun.neverth.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import fun.neverth.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 18:23
 */
@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    private static final String GATEWAY_ROUTES = "icibei_gateway_routes::";

    /**
     * \@Resource的作用相当于@Autowired，
     * 只不过@Autowired按byType自动注入，
     * 而@Resource默认按 byName自动注入罢了。
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> routeCache;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    /**
     * 1. 构造函数 -> postConstruct -> init ->
     * 2. 只会被服务器调用一次
     */
    @PostConstruct
    private void initRouteDefinition(){
        log.info("loadRouteDefinition, 开始从redis读取路由");

        Set<String> routeKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");
        if (CollectionUtils.isEmpty(routeKeys)){
            return;
        }

        Set<String> routeIds = routeKeys.stream().map(
                key -> key.replace(GATEWAY_ROUTES, "")
        ).collect(Collectors.toSet());

        Map<String, RouteDefinition> allRoute = routeCache.getAll(routeIds);

        routeDefinitionMaps.putAll(allRoute);

        log.info("共初始化路由信息：{}个。", routeDefinitionMaps.size());
    }

    @Override
    public boolean saveRouteDefinition(RouteDefinition routeDefinition) {
        return false;
    }

    @Override
    public boolean deleteRouteDefinition(String routeId) {
        return false;
    }

    @Override
    public boolean updateRouteDefinition(RouteDefinition routeDefinition) {
        return false;
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMaps.values();
    }
}
