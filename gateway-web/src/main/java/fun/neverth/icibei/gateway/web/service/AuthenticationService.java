package fun.neverth.icibei.gateway.web.service;

import fun.neverth.icibei.common.core.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * @author NeverTh
 * @date 13:36 2020/10/6
 */
public interface AuthenticationService {
    /**
     * 调用签权服务，判断用户是否有权限
     */
    Result<String> authenticate(String authentication, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     */
    boolean ignoreAuthentication(String url);

    /**
     * 查看签权服务器返回结果，有权限返回true
     */
    boolean hasPermission(Result authResult);

    /**
     * 调用签权服务，判断用户是否有权限
     */
    boolean hasPermission(String authentication, String url, String method);

    /**
     * 是否无效authentication
     */
    boolean invalidJwtAccessToken(String authentication);

    /**
     * 从认证信息中提取jwt token 对象
     */
    Jws<Claims> parseJwtToken(String jwtToken);

}
