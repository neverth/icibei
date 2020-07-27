package fun.neverth.icibei.organization.entity.po;

import fun.neverth.icibei.common.web.po.BasePO;
import lombok.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BasePO {

    private String code;

    private String type;

    private String url;

    private String method;

    private String name;

    private String description;

}
