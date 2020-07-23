package fun.neverth.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
