package fun.neverth.icibei.sysadmin.organization.service;

import fun.neverth.icibei.sysadmin.organization.entity.param.GroupQueryParam;
import fun.neverth.icibei.sysadmin.organization.entity.po.Group;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:19
 */
public interface GroupService {

    Group get(String id);


    boolean add(Group group);

    List<Group> query(GroupQueryParam groupQueryParam);

    /**
     * 根据父id查询用户组
     */
    List<Group> queryByParentId(String id);

    boolean update(Group group);

    boolean delete(String id);
}
