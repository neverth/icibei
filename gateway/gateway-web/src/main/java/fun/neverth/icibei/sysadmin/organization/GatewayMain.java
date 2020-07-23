package fun.neverth.icibei.sysadmin.organization;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 17:49
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "fun.neverth")
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class, args);
    }
}
