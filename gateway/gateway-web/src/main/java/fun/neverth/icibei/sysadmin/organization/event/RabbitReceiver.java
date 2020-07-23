package fun.neverth.icibei.sysadmin.organization.event;

import fun.neverth.icibei.sysadmin.organization.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 15:16
 */
@Component
@Slf4j
public class RabbitReceiver {

    @Resource
    RouteService routeService;

    /**
     * handleMessage 是 MessageListenerAdapter 类重指定处理消息的方法名
     */
    public void handleMessage(RouteDefinition routeDefinition) {
        log.info("处理消息 Message:<{}>", routeDefinition);
        routeService.save(routeDefinition);
    }
}
