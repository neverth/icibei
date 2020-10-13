package fun.neverth.icibei.organization.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author NeverTh
 * @date 13:17 2020/10/11
 */
@Data
public class UserInfoVO {
    /**
     * 唯一Id
     */
    private String userId;
    /**
     * 账户ID，唯一
     */
    private String accountId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 别名
     */
    private String nickName;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 头像网址
     */
    private String avatar;
    /**
     * 角色
     */
    private Set<String> roles;
}
