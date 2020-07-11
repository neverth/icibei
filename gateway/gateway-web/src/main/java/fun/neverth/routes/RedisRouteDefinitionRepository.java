package fun.neverth.routes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 18:07
 */
//@Slf4j
//@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    /**
     * getRouteDefinitions() 每次请求都会调用
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
