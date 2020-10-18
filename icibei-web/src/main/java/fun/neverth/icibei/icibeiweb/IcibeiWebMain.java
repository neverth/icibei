package fun.neverth.icibei.icibeiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author NeverTh
 * @date 22:31 2020/10/18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class IcibeiWebMain {
    public static void main(String[] args) {
        SpringApplication.run(IcibeiWebMain.class, args);
    }
}
