package fun.neverth.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePO implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
