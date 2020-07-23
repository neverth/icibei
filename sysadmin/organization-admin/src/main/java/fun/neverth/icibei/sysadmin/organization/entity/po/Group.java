package fun.neverth.icibei.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("groups")
public class Group extends BasePO {

    private String name;

    private String parentId;

    private String description;

    @TableLogic
    private String deleted = "N";
}
