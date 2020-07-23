package fun.neverth.icibei.sysadmin.organization.entity.vo;

import fun.neverth.icibei.sysadmin.organization.entity.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
