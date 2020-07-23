package fun.neverth.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import fun.neverth.entity.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserVO extends BaseVO{
    private String id;

    private String name;

    private String mobile;

    private String username;

    private String password;

    private String description;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    private Set<String> roleIds;

    private String deleted;

    public UserVO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
