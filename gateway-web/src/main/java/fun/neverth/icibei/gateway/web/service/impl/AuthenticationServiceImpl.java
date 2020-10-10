package fun.neverth.icibei.gateway.web.service.impl;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.gateway.web.provider.AuthenticationServerProvider;
import fun.neverth.icibei.gateway.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author NeverTh
 * @date 13:36 2020/10/6
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    @Autowired
    private AuthenticationServerProvider authenticationServerProvider;

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authenticationServerProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Arrays.stream(ignoreUrls.split(",")).anyMatch(url::startsWith);
    }

    @Override
    public boolean hasPermission(Result authResult) {
        return false;
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        return false;
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        return false;
    }
}
