package fun.neverth.icibei.sysadmin.organization.entity.param;

import fun.neverth.icibei.sysadmin.organization.entity.po.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 21:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceQueryParam extends BaseParam<Resource>{
    private String name;
    private String code;
    private String type;
    private String url;
    private String method;
}
