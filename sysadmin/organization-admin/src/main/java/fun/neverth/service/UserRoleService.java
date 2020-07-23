package fun.neverth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.entity.param.UserQueryParam;
import fun.neverth.entity.po.User;
import fun.neverth.entity.vo.UserVO;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface UserRoleService {

    /**
     * 给用户添加角色
     */
    boolean saveBatch(String userId, Set<String> roleIds);

    /**
     * 删除用户拥有的角色
     */
    boolean removeByUserId(String userId);

    /**
     * 根据userId查询用户拥有角色id集合
     */
    Set<String> queryByUserId(String userId);
}
