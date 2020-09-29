package fun.neverth.icibei.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author NeverTh
 * @date 0:11 2020/9/25
 */
@SpringBootApplication
@EnableFeignClients
public class AuthenticationMain {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationMain.class, args);
    }
}
