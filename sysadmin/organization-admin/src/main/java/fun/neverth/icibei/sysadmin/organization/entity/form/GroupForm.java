package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.po.Group;
import fun.neverth.icibei.common.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupForm extends BaseForm<Group> {

    @NotBlank(message = "用户组父id不能为空")
    private String parentId;

    @NotBlank(message = "用户组名称不能为空")
    private String name;

    private String description;
}
