package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("role_resource_relation")
public class RoleResource extends BasePO {

    private String roleId;

    private String resourceId;
}
