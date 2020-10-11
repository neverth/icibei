package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id，外键user表id
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


}
