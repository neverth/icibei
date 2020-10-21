package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.common.web.po.BasePO;
import fun.neverth.icibei.organization.dao.ResourceMapper;
import fun.neverth.icibei.organization.entity.po.Resource;
import fun.neverth.icibei.organization.entity.po.Role;
import fun.neverth.icibei.organization.entity.po.RoleResource;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.entity.vo.UserVO;
import fun.neverth.icibei.organization.service.ResourceService;
import fun.neverth.icibei.organization.service.RoleResourceService;
import fun.neverth.icibei.organization.service.RoleService;
import fun.neverth.icibei.organization.service.UserService;
import fun.neverth.icibei.organization.entity.param.ResourceQueryParam;
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


    @Override
    public boolean add(Resource resource) {
//        eventSender.send(RabbitConfig.ROUTING_KEY, resource);
        return this.save(resource);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public boolean update(Resource resource) {
        return this.updateById(resource);
    }

    @Override
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
    public List<Resource> query(String userId) {
        List<Role> roles = roleService.query(userId);
        // 提取用户所拥有角色id列表
        Set<String> roleIds = roles.stream().map(BasePO::getId).collect(Collectors.toSet());
        // 根据角色列表查询到角色的资源的关联关系
        List<RoleResource> roleResources = roleResourceService.queryByRoleIds(roleIds);
        // 根据资源列表查询出所有资源对象
        Set<String> resourceIds = roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
        //根据resourceId列表查询出resource对象
        return this.listByIds(resourceIds);
    }
}
