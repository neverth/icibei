package fun.neverth.icibei.sysadmin.organization.entity.param;

import fun.neverth.icibei.sysadmin.organization.entity.po.GatewayRoute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 16:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute>{
    private String uri;
}
