package fun.neverth.icibei.authentication.service.impl;

import fun.neverth.icibei.authentication.service.AuthenticationService;
import fun.neverth.icibei.authentication.service.ResourceService;
import fun.neverth.icibei.organization.entity.po.IResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author neverth.li
 * @date 2020/9/29 14:38
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "nonexistent_url";

    @Autowired
    private ResourceService resourceService;

    @Override
    public String decide(HttpServletRequest authRequest) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        // 获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取此url，method访问对应的权限资源信息
        ConfigAttribute urlConfigAttribute = resourceService.findConfigAttributesByUrl(authRequest);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute())) {
            log.debug("url未在资源池中找到，拒绝访问");
        }
        // 获取此访问用户所有角色拥有的权限资源
        Set<IResource> userResources = findResourcesByUserId(authentication.getName());
        // 用户拥有权限资源 与 url要求的资源进行对比
        // return isMatch(urlConfigAttribute, userResources) ? authentication.getName(): "-1";
        return authentication.getName();
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     */
    private Set<IResource> findResourcesByUserId(String userId) {
        // 用户被授予的角色资源
        Set<IResource> resources = resourceService.queryByUserId(userId);
        if (log.isDebugEnabled()) {
            log.debug("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", resources.size(), resources);
        }
        return resources;
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<IResource> userResources) {
        return userResources.stream().anyMatch(resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
    }
}
