package fun.neverth.icibei.sysadmin.organization.entity.po;

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
public class Menu extends BasePO {

    private String parentId;

    private String name;

    private String type;

    private String href;

    private String icon;

    private int orderNum;

    private String description;
}
