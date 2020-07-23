package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.po.User;
import fun.neverth.icibei.common.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateForm extends BaseForm<User> {


    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;


    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;


    private String mobile;


    private String name;


    private String description;


    private Set<String> roleIds;


    private Boolean enabled = true;


    private Boolean accountNonExpired = true;


    private Boolean credentialsNonExpired = true;


    private Boolean accountNonLocked = true;
}
