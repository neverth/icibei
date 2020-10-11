package fun.neverth.icibei.organization.service.impl;

import fun.neverth.icibei.organization.entity.po.UserInfo;
import fun.neverth.icibei.organization.dao.UserInfoMapper;
import fun.neverth.icibei.organization.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
