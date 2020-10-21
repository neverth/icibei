package fun.neverth.icibei.authentication.service;

import fun.neverth.icibei.organization.entity.po.IResource;
import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author neverth.li
 * @date 2020/9/29 14:40
 */
public interface ResourceService {
    /**
     * 根据用户名查询 该用户所拥有的角色对应的资源信息
     */
    Set<IResource> queryByUserId(String username);

    /**
     * 根据url和method查询到对应的权限信息
     */
    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest);
}
