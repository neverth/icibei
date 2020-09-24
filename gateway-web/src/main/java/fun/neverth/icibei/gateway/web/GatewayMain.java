package fun.neverth.icibei.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 17:49
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class, args);
    }
}
