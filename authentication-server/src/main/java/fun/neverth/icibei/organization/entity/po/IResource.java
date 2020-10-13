package fun.neverth.icibei.organization.entity.po;
import lombok.*;

import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:14
 */
@Data
public class IResource {
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
     * 资源方法 POST GET
     */
    private String method;

    /**
     * 简介
     */
    private String description;

    private String id;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
}
