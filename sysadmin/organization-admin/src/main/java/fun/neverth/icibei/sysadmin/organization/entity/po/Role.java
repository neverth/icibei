package fun.neverth.icibei.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Set;

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
@TableName("roles")
public class Role extends BasePO {

    private String code;

    private String name;

    private String description;

    @TableField(exist = false)
    private Set<String> resourceIds;
}
