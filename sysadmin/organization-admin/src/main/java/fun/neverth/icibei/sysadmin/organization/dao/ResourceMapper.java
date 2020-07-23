package fun.neverth.icibei.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.neverth.icibei.sysadmin.organization.entity.po.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 21:51
 */
@Repository
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}