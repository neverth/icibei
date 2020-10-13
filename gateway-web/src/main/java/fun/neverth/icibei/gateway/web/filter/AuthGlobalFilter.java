package fun.neverth.icibei.gateway.web.filter;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.gateway.web.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;


/**
 * @author NeverTh
 * @date 23:30 2020/9/24
 */
@Configuration
@Slf4j
public class AuthGlobalFilter implements GlobalFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        // 不需要鉴权的url
        if (authenticationService.ignoreAuthentication(url)){
            return chain.filter(exchange);
        }
        Result authenticate = authenticationService.authenticate(authentication, url, method);
        if (authenticate.isSuccess()) {
            if ((boolean) authenticate.getData()) {
                ServerHttpRequest.Builder builder = request.mutate();
                builder.header("USERID", "");
                return chain.filter(exchange.mutate().request(builder.build()).build());
            }
        }
        return unauthorized(exchange);
    }

    /**
     * 网关拒绝，返回401
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
