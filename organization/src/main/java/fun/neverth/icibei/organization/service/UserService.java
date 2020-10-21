package fun.neverth.icibei.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.icibei.organization.entity.form.UserForm;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.entity.vo.UserVO;
import fun.neverth.icibei.organization.entity.param.UserQueryParam;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface UserService {

    /**
     * 根据用户id获取用户
     */
    UserVO get(String id);

    /**
     * 根据用户名或者手机号获取用户信息
     */
    UserVO getByUniqueId(String uniqueId);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean add(User user);

    boolean add(UserForm userForm);

    /**
     * 查询用户
     *
     * @return
     */
    IPage<UserVO> query(Page<User> page, UserQueryParam userQueryParam);

    /**
     * 更新用户信息
     *
     * @param user
     */
    boolean update(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    boolean delete(String id);
}
