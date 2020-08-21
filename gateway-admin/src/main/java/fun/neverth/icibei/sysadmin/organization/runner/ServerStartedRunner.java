package fun.neverth.icibei.sysadmin.organization.runner;

import fun.neverth.icibei.sysadmin.organization.service.GatewayRouteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 17:27
 */
@Component
public class ServerStartedRunner implements CommandLineRunner {

    @Resource
    GatewayRouteService gatewayRouteService;

    @Override
    public void run(String... args) throws Exception {
        gatewayRouteService.overLoadToCache();
    }
}
