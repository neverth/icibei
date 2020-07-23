package fun.neverth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.neverth.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:17
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
