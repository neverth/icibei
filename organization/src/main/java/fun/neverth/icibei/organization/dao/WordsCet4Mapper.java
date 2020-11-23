package fun.neverth.icibei.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author neverth.li
 * @date 2020/10/10 16:06
 */
@Repository
@Mapper
public interface WordsCet4Mapper extends BaseMapper<WordsCet4> {
    @Select("select * from words_cet4 order by rand() limit 0,#{size};")
    List<WordsCet4> selectRandom(@Param("size") int size);
}
