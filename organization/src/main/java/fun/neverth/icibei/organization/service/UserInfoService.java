package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.po.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.neverth.icibei.organization.entity.vo.UserInfoVO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
public interface UserInfoService extends IService<UserInfo> {
    UserInfo getByUserId(String userId);

    UserInfoVO getVoByUserId(String userId);
}