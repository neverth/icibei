package fun.neverth.icibei.authentication.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author neverth.li
 * @date 2020/9/29 14:38
 */
public interface AuthenticationService {
    /**
     * 校验权限
     */
    String decide(HttpServletRequest authRequest);
}
