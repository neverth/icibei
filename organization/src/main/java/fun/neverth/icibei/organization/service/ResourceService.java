package fun.neverth.icibei.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.icibei.organization.entity.po.Resource;
import fun.neverth.icibei.organization.entity.param.ResourceQueryParam;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface ResourceService {


    Resource get(String id);


    boolean add(Resource resource);

    /**
     * 查询资源,分页
     */
    IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam);

    List<Resource> getAll();

    /**
     * 根据username查询角色拥有的资源
     */
    List<Resource> query(String username);

    boolean update(Resource resource);

    boolean delete(String id);
}
