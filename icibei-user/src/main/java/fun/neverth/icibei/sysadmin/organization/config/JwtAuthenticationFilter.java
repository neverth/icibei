package fun.neverth.icibei.sysadmin.organization.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/9 18:19
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() != null) {

            filterChain.doFilter(httpServletRequest, httpServletResponse);

            return;
        }

        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (StringUtils.hasText(token) && token.startsWith(AUTHENTICATION_PREFIX)) {
//
//            String jwtToken = token.replace(AUTHENTICATION_PREFIX, "");
//
//            if (StringUtils.hasText(jwtToken)) {
//                try {
//                    authenticationTokenHandle(jwtToken, request);
//
//                } catch (AuthenticationException e) {
//
//                    authenticationEntryPoint.commence(request, response, e);
//
//                }
//            } else {
//
//                authenticationEntryPoint.commence(request, response, new AuthenticationCredentialsNotFoundException("token is not found"));
//            }
//        }

//        JWT.create()

    }
}
