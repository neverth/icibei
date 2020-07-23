package fun.neverth.icibei.auth.authorization.enhancer;


import fun.neverth.icibei.auth.authorization.entity.SecurityUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 15:44
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken oAuth2AccessToken,
            OAuth2Authentication oAuth2Authentication
    ) {
        SecurityUser securityUser = (SecurityUser)oAuth2Authentication.getPrincipal();
        return oAuth2AccessToken;
    }
}
