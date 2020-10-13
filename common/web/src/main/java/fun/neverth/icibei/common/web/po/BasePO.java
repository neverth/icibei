package fun.neverth.icibei.common.web.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:11
 */
@Data
public class BasePO implements Serializable {

    public final static String DEFAULT_USERNAME = "system";
    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
