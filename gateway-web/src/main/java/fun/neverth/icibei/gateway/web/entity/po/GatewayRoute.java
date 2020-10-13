package fun.neverth.icibei.gateway.web.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author NeverTh
 * @date 10:14 2020/10/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("gateway_route")
public class GatewayRoute extends BasePO {
    /**
     * 路由id
     */
    private String routeId;
    /**
     * uri路径
     */
    private String uri;
    /**
     * 判定器
     */
    private String predicates;
    /**
     * 过滤器
     */
    private String filters;
    /**
     * 排序
     */
    private Integer orders = 0;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态：Y-有效，N-无效
     */
    private String status = "Y";
}
