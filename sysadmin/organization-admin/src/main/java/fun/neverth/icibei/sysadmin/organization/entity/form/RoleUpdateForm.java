package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.po.Role;
import fun.neverth.icibei.common.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleUpdateForm extends BaseForm<Role> {


    private String code;


    private String name;


    private String description;


    private Set<String> resourceIds;

}
