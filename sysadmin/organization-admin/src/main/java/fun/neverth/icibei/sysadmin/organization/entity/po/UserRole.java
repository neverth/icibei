package fun.neverth.icibei.sysadmin.organization.entity.po;

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
@TableName("user_role_relation")
public class UserRole extends BasePO {

    private String userId;

    private String roleId;
}
