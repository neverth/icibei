package fun.neverth.icibei.gateway.web.exception;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * {@link org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration}
 *
 * @author NeverTh
 * @date 15:17 2020/10/6
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnClass(WebFluxConfigurer.class)
@AutoConfigureBefore(WebFluxAutoConfiguration.class)
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class ExceptionAutoConfiguration {

    private final ServerProperties serverProperties;

    public ExceptionAutoConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ObjectProvider<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext) {
        DefaultErrorWebExceptionHandler exceptionHandler = new CustomErrorWebExceptionHandler(errorAttributes, resourceProperties, this.serverProperties.getError(), applicationContext);
        exceptionHandler.setViewResolvers((List)viewResolvers.orderedStream().collect(Collectors.toList()));
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }
}
