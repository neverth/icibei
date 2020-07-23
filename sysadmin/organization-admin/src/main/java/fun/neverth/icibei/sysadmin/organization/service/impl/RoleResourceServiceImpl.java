package fun.neverth.icibei.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.sysadmin.organization.dao.RoleResourceMapper;
import fun.neverth.icibei.sysadmin.organization.entity.po.RoleResource;
import fun.neverth.icibei.sysadmin.organization.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:01
 */
@Service
@Slf4j
public class RoleResourceServiceImpl
        extends ServiceImpl<RoleResourceMapper, RoleResource>
        implements RoleResourceService {

    @Override
    public boolean saveBatch(String roleId, Set<String> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)){
            return false;
        }
        removeByRoleId(roleId);
        Set<RoleResource> userRoles = resourceIds.stream().map(resourceId -> new RoleResource(roleId, resourceId)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    public boolean removeByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        return remove(queryWrapper);
    }

    @Override
    @Cached(area = "shortTime", name = "resource4role::", key = "#roleId", cacheType = CacheType.BOTH)
    public Set<String> queryByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleResource> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<String> roleIds) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }
}
