package fun.neverth.icibei.sysadmin.organization.entity.form;

import fun.neverth.icibei.sysadmin.organization.entity.param.GatewayRouteQueryParam;
import fun.neverth.icibei.common.web.form.BaseQueryForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 16:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GatewayRouteQueryForm extends BaseQueryForm<GatewayRouteQueryParam> {
    private String uri;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    private Date createdTimeEnd;
}
