package fun.neverth.icibei.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("users")
public class User extends BasePO {

    private String name;

    private String mobile;

    private String username;

    private String password;

    private String description;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    @TableField(exist = false)
    private Set<String> roleIds;

    @TableLogic
    private String deleted = "N";
}
