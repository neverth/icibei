package fun.neverth.icibei.organization.entity.form;

import fun.neverth.icibei.organization.entity.param.ResourceQueryParam;
import fun.neverth.icibei.common.web.form.BaseQueryForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PositionQueryForm extends BaseQueryForm<ResourceQueryParam> {

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")

    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")

    private Date createdTimeEnd;
}
