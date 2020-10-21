package fun.neverth.icibei.authentication.service.impl;

import com.google.common.base.Objects;
import fun.neverth.icibei.authentication.provider.ResourceProvider;
import fun.neverth.icibei.authentication.service.ResourceService;
import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.po.IResource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author neverth.li
 * @date 2020/9/29 14:42
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "nonexistent_url";

    /**
     * 系统中所有权限集合
     */
    private static final Map<RequestMatcher, ConfigAttribute> RESOURCE_CONFIG_ATTRIBUTES = new HashMap<>();

    /**
     * 这是一个Spring MVC助手类，用于集合应用所配置的HandlerMapping(url pattern和请求处理handler之间的映射)表
     */
    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Resource
    private ResourceProvider resourceProvider;

    @PostConstruct
    private void loadResource() {
        Result<Set<IResource>> resourcesResult = resourceProvider.resources();
        if (resourcesResult.isFail()) {
            log.error("加载所有资源失败");
            System.exit(1);
        }
        for (IResource resource : resourcesResult.getData()) {
            // 初始化全局系统中所有权限集合
            RESOURCE_CONFIG_ATTRIBUTES.put(
                    new IMvcRequestMatcher(mvcHandlerMappingIntrospector, resource.getUrl(), resource.getMethod()),
                    new SecurityConfig(resource.getCode())
            );
        }
        log.debug("初始化系统中所有权限集合resourceConfigAttributes:{}", RESOURCE_CONFIG_ATTRIBUTES);
    }

    @Override
    public Set<IResource> queryByUserId(String userId) {
        Result<Set<IResource>> resources = resourceProvider.resources(userId);
        if (resources.isSuccess()) {
            return resources.getData();
        }
        return null;
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        for (RequestMatcher requestMatcher : RESOURCE_CONFIG_ATTRIBUTES.keySet()) {
            if (requestMatcher.matches(authRequest)){
                return RESOURCE_CONFIG_ATTRIBUTES.get(requestMatcher);
            }
        }
        return new SecurityConfig(NONEXISTENT_URL);
    }

    @Getter
    static class IMvcRequestMatcher extends MvcRequestMatcher {
        private final String pattern;
        private final String method;

        public IMvcRequestMatcher(HandlerMappingIntrospector introspector, String pattern, String method) {
            super(introspector, pattern);
            this.setMethod(HttpMethod.resolve(method));
            this.pattern = pattern;
            this.method = method;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IMvcRequestMatcher that = (IMvcRequestMatcher) o;
            return Objects.equal(pattern, that.pattern) && Objects.equal(method, that.method);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(pattern, method);
        }
    }
}
