package fun.neverth.icibei.gateway.web.service.impl;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.gateway.web.provider.AuthenticationServerProvider;
import fun.neverth.icibei.gateway.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AuthenticationServerProvider authenticationServerProvider;

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authenticationServerProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return false;
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
