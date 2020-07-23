package fun.neverth.icibei.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.icibei.sysadmin.organization.entity.param.UserQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.User;
import fun.neverth.icibei.sysadmin.organization.entity.vo.UserVO;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param id 用户id
     * @return UserVo
     */
    UserVO get(String id);

    /**
     * 根据用户唯一标识获取用户信息
     * 目前用户标识不用户名或mobile
     *
     * @param uniqueId
     * @return
     */
    User getByUniqueId(String uniqueId);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean add(User user);

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
