package fun.neverth.icibei.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.sysadmin.organization.config.RabbitConfig;
import fun.neverth.icibei.sysadmin.organization.dao.ResourceMapper;
import fun.neverth.icibei.sysadmin.organization.entity.param.ResourceQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.Resource;
import fun.neverth.icibei.sysadmin.organization.entity.po.Role;
import fun.neverth.icibei.sysadmin.organization.entity.po.RoleResource;
import fun.neverth.icibei.sysadmin.organization.entity.po.User;
import fun.neverth.icibei.sysadmin.organization.event.EventSender;
import fun.neverth.icibei.sysadmin.organization.service.ResourceService;
import fun.neverth.icibei.sysadmin.organization.service.RoleResourceService;
import fun.neverth.icibei.sysadmin.organization.service.RoleService;
import fun.neverth.icibei.sysadmin.organization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:57
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
        implements ResourceService {

    @javax.annotation.Resource
    private RoleResourceService roleResourceService;

    @javax.annotation.Resource
    private RoleService roleService;

    @javax.annotation.Resource
    private UserService userService;

    @javax.annotation.Resource
    private EventSender eventSender;

    @Override
    public boolean add(Resource resource) {
        eventSender.send(RabbitConfig.ROUTING_KEY, resource);
        return this.save(resource);
    }

    @Override
    @Cached(name = "resource::", key = "#id", cacheType = CacheType.BOTH)
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @Cached(name = "resource::", key = "#resource.id", cacheType = CacheType.BOTH)
    public boolean update(Resource resource) {
        return this.updateById(resource);
    }

    @Override
    @Cached(name = "resource::", key = "#id", cacheType = CacheType.BOTH)
    public Resource get(String id) {
        return this.getById(id);
    }

    @Override
    public IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam) {
        QueryWrapper<Resource> queryWrapper = resourceQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getName()), "name", resourceQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getType()), "type", resourceQueryParam.getType());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getUrl()), "url", resourceQueryParam.getUrl());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getMethod()), "method", resourceQueryParam.getMethod());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Resource> getAll() {
        return this.list();
    }

    @Override
    @Cached(name = "resource4user::", key = "#username", cacheType = CacheType.BOTH)
    public List<Resource> query(String username) {
        //根据用户名查询到用户所拥有的角色
        User user = userService.getByUniqueId(username);
        List<Role> roles = roleService.query(user.getId());
        //提取用户所拥有角色id列表
        Set<String> roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toSet());
        //根据角色列表查询到角色的资源的关联关系
        List<RoleResource> roleResources = roleResourceService.queryByRoleIds(roleIds);
        //根据资源列表查询出所有资源对象
        Set<String> resourceIds = roleResources.stream().map(roleResource -> roleResource.getResourceId()).collect(Collectors.toSet());
        //根据resourceId列表查询出resource对象
        return (List<Resource>) this.listByIds(resourceIds);
    }
}
