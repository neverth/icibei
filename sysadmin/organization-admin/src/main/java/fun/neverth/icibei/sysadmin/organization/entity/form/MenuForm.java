package fun.neverth.icibei.sysadmin.organization.entity.form;


import fun.neverth.icibei.sysadmin.organization.entity.po.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuForm extends BaseForm<Menu> {

    @NotBlank(message = "菜单父id不能为空")
    private String parentId;

    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @NotBlank(message = "菜单类型不能为空")
    private String type;

    @NotBlank(message = "菜单路径不能为空")
    private String href;

    private String icon;

    private String orderNum;

    private String description;
}
