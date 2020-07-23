package fun.neverth.icibei.sysadmin.organization.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 16:52
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
