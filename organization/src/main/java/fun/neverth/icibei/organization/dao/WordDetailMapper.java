package fun.neverth.icibei.organization.dao;

import fun.neverth.icibei.organization.entity.po.WordDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@Mapper
@Repository
public interface WordDetailMapper extends BaseMapper<WordDetail> {

}
