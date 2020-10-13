package fun.neverth.icibei.organization.dao;

import fun.neverth.icibei.organization.entity.po.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
@Repository
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
