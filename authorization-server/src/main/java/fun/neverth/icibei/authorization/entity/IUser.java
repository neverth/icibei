package fun.neverth.icibei.authorization.entity;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 16:44
 */
@Data
public class IUser{

    private String id;

    private String name;

    private String mobile;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

}
