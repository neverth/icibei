package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.GroupMapper;
import fun.neverth.icibei.organization.entity.po.Group;
import fun.neverth.icibei.organization.service.GroupService;
import fun.neverth.icibei.organization.entity.param.GroupQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 19:58
 */
@Service
@Slf4j
public class GroupServiceImpl
        extends ServiceImpl<GroupMapper, Group>
        implements GroupService {

    @Override
    public boolean add(Group group) {
        return this.save(group);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public boolean update(Group group) {
        return this.updateById(group);
    }

    @Override
    public Group get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = groupQueryParam.build();
        queryWrapper.eq("name", groupQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Group> queryByParentId(String id) {
        return this.list(new QueryWrapper<Group>().eq("parent_id", id));
    }

}
