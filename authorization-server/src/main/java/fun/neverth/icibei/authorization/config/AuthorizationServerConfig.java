package fun.neverth.icibei.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * oauth认证服务器
 *
 * @author NeverTh
 * @date 2020/7/17 16:54
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许客户端使用client_id和client_secret获取token
        security.allowFormAuthenticationForClients()
                // /oauth/check_token 端点的开放
                .tokenKeyAccess("isAuthenticated()")
                // 获取token的请求不拦截
                .checkTokenAccess("permitAll()");
    }

    /**
     * 配置客户端信息
     * 从数据库中读取，对应oauth_client_details表
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        // 构建一个 id 为 client 的客户端
        builder.withClient("client")
                // 设置她的密钥，加密后的
                .secret(passwordEncoder.encode("123"))
                // 设置允许访问的资源 id
                .resourceIds("WEBS")
                // 授权的类型
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                // 可以授权的角色
                .authorities("ROLE_ADMIN", "ROLE_USER")
                // 授权的范围
                .scopes("all")
                // token 有效期
                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(12).getSeconds()))
                // 刷新 token 的有效期
                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(12).getSeconds()))
                // 授权码模式的重定向地址
                .redirectUris("http://example.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 复合令牌增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter()));
        // 配置token的数据源、自定义的tokenServices等信息,配置身份认证器，配置认证方式，
        // TokenStore，TokenGranter，OAuth2RequestFactory
        endpoints.authenticationManager(authenticationManager)
                // 保存token，因为是jwt，因此不需要保存任何数据
                .tokenStore(tokenStore())
                // token转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                // token增强器
                .tokenEnhancer(enhancerChain)
                // 需要重新指定userDetailsService
                .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token转换器，非对称加密
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 秘钥对
     * 1. keytool -genkey -alias icibei -keyalg RSA -keystore icibei.jks -keysize 2048
     * 2. keytool -importkeystore -srckeystore icibei.jks -destkeystore icibei.jks -deststoretype pkcs12
     * 3. keytool -list -rfc --keystore icibei.jks
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("icibei.jks"), "icibei".toCharArray()
        );
        return keyStoreKeyFactory.getKeyPair("icibei", "icibei".toCharArray());
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("organization", authentication.getName());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

}
