package fun.neverth.icibei.auth.authorization.entity;

import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 16:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User extends BasePO {

    private String name;

    private String mobile;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

}
