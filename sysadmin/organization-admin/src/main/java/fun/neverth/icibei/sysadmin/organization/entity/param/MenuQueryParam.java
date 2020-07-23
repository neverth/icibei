package fun.neverth.icibei.sysadmin.organization.entity.param;

import fun.neverth.icibei.sysadmin.organization.entity.po.Menu;
import fun.neverth.icibei.common.web.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 21:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuQueryParam extends BaseParam<Menu> {
    private String name;
}
