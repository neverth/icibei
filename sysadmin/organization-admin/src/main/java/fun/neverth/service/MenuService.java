package fun.neverth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.entity.param.UserQueryParam;
import fun.neverth.entity.po.Menu;
import fun.neverth.entity.po.User;
import fun.neverth.entity.vo.UserVO;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface MenuService {

    Menu get(String id);

    boolean add(User user);

    boolean update(User user);

    boolean delete(String id);
}
