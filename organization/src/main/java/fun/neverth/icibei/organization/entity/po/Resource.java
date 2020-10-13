package fun.neverth.icibei.organization.entity.po;

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
public class Resource extends BasePO {
    /**
     * 资源code
     */
    private String code;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    private String url;

    /**
     * 资源方法
     */
    private String method;

    /**
     * 简介
     */
    private String description;
}
