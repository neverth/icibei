package fun.neverth.icibei.gateway.web.entity.vo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.neverth.icibei.gateway.web.entity.po.GatewayRoute;
import fun.neverth.icibei.common.web.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:34
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class GatewayRouteVO extends BaseVO {
    private String id;
    private String routeId;
    private String description;
    private String status;
    private String uri;
    private Integer orders;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();

    public GatewayRouteVO(GatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId();
        this.routeId = gatewayRoute.getRouteId();
        this.uri = gatewayRoute.getUri();
        this.description = gatewayRoute.getDescription();
        this.status = gatewayRoute.getStatus();
        this.orders = gatewayRoute.getOrders();
        this.createdBy = gatewayRoute.getCreatedBy();
        this.createdTime = gatewayRoute.getCreatedTime();
        this.updatedBy = gatewayRoute.getUpdatedBy();
        this.updatedTime = gatewayRoute.getUpdatedTime();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            });
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
    }
}
