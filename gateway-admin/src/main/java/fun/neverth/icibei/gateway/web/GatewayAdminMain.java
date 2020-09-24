package fun.neverth.icibei.gateway.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 22:48
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "fun.neverth")
@EnableCreateCacheAnnotation
public class GatewayAdminMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminMain.class, args);
    }
}
