package fun.neverth.icibei.sysadmin.organization.service;

import fun.neverth.icibei.sysadmin.organization.entity.param.MenuQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.Menu;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface MenuService {

    Menu get(String id);

    boolean add(Menu menu);

    List<Menu> query(MenuQueryParam menuQueryParam);

    /**
     * 根据父id查询菜单
     */
    List<Menu> queryByParentId(String id);

    boolean update(Menu menu);

    boolean delete(String id);
}
