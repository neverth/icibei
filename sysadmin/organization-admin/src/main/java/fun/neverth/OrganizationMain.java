package fun.neverth;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/19 11:08
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "fun.neverth")
@EnableCreateCacheAnnotation
public class OrganizationMain {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationMain.class, args);
    }
}
