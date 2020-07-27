package fun.neverth.icibei.organization.entity.form;

import fun.neverth.icibei.organization.entity.po.Role;
import fun.neverth.icibei.common.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleForm extends BaseForm<Role> {

    @NotBlank(message = "角色编码不能为空")

    private String code;

    @NotBlank(message = "角色名称不能为空")

    private String name;


    private String description;


    private Set<String> resourceIds;

}
