package fun.neverth.icibei.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 17:49
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "fun.neverth.icibei.gateway.web.provider")
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class, args);
    }
}
