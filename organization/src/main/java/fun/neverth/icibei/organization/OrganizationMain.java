package fun.neverth.icibei.organization;

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
public class OrganizationMain {
    //
    public static void main(String[] args) {
        SpringApplication.run(OrganizationMain.class, args);
    }
}
