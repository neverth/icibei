package fun.neverth.icibei.organization.dao;

import fun.neverth.icibei.organization.entity.po.UserWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户单词信息表 Mapper 接口
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@Mapper
@Repository
public interface UserWordMapper extends BaseMapper<UserWord> {

}
