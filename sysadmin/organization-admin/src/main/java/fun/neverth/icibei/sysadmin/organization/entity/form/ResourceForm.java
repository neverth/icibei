package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.po.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceForm extends BaseForm<Resource> {

    @NotBlank(message = "资源名称不能为空")

    private String name;

    @NotBlank(message = "资源编码不能为空")

    private String code;

    @NotBlank(message = "资源类型不能为空")

    private String type;

    @NotBlank(message = "资源路径不能为空")

    private String url;

    @NotBlank(message = "资源方法不能为空")

    private String method;


    private String description;
}
