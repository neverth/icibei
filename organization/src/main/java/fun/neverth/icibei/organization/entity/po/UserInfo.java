package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import fun.neverth.icibei.common.web.po.BasePO;
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
    private String signature = "该用户很懒，并没有个性签名哦！";

    /**
     * 头像网址
     */
    private String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603010663945&di=2d38f9f3a19d8514fc69510f35a45e89&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201603%2F06%2F20160306204517_i4Se8.jpeg";


}
