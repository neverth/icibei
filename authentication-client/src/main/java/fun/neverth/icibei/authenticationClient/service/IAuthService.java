package fun.neverth.icibei.authenticationClient.service;

import fun.neverth.icibei.common.core.vo.Result;

/**
 * @author NeverTh
 * @date 11:19 2020/10/3
 */
public interface IAuthService {
    /**
     * 调用签权服务，判断用户是否有权限
     */
    Result authenticate(String authentication, String url, String method);

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
}
