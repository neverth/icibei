package fun.neverth.icibei.gateway.web.entity.param;

import fun.neverth.icibei.gateway.web.entity.po.GatewayRoute;
import fun.neverth.icibei.common.web.param.BaseParam;
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
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute> {
    private String uri;
}
