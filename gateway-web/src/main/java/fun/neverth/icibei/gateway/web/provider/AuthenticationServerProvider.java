package fun.neverth.icibei.gateway.web.provider;

import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author NeverTh
 * @date 11:34 2020/10/3
 */
@Component
@FeignClient(name = "icibei-authorization-server")
public interface AuthenticationServerProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     */
    @PostMapping(value = "/auth/permission")
    Result<String> auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

}
