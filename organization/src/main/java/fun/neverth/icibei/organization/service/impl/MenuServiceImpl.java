package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.MenuMapper;
import fun.neverth.icibei.organization.entity.po.Menu;
import fun.neverth.icibei.organization.service.MenuService;
import fun.neverth.icibei.organization.entity.param.MenuQueryParam;
import org.springframework.boot.autoconfigure.cache.CacheType;
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
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
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
