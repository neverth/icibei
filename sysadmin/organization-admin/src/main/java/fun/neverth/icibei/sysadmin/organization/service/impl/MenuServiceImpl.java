package fun.neverth.icibei.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.sysadmin.organization.dao.MenuMapper;
import fun.neverth.icibei.sysadmin.organization.entity.param.MenuQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.Menu;
import fun.neverth.icibei.sysadmin.organization.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:58
 */
@Service
public class MenuServiceImpl
        extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {
    @Override
    public boolean add(Menu menu) {
        return this.save(menu);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
    @Cached(name = "menu::", key = "#id", cacheType = CacheType.BOTH)
    public Menu get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(), "name", menuQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(String id) {
        return this.list(new QueryWrapper<Menu>().eq("parent_id", id));
    }

}
