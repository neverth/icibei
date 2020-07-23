package fun.neverth.icibei.sysadmin.organization.entity.param;

import fun.neverth.icibei.sysadmin.organization.entity.po.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryParam extends BaseParam<Role>{
    private String code;
    private String name;
}
