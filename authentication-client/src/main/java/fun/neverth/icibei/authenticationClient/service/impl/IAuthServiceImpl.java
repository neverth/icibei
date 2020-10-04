package fun.neverth.icibei.authenticationClient.service.impl;

import fun.neverth.icibei.authenticationClient.provider.AuthenticationServerProvider;
import fun.neverth.icibei.authenticationClient.service.IAuthService;
import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AuthProvider;

/**
 * @author NeverTh
 * @date 11:19 2020/10/3
 */
@Service
public class IAuthServiceImpl implements IAuthService {
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
