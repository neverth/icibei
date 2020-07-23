package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserForm extends BaseForm<User> {


    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;


    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;


    @NotBlank(message = "用户手机号不能为空")
    private String mobile;


    @NotBlank(message = "用户姓名不能为空")
    private String name;


    private String description;


    private Set<String> roleIds;


    private Boolean enabled = true;


    private Boolean accountNonExpired = true;


    private Boolean credentialsNonExpired = true;


    private Boolean accountNonLocked = true;
}
