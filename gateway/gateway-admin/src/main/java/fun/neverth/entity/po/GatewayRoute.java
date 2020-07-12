package fun.neverth.entity.po;

import fun.neverth.entity.po.BasePO;
import lombok.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BasePO {
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders = 0;
    private String status = "Y";
}
