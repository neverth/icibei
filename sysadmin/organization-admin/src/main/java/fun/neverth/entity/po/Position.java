package fun.neverth.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Position extends BasePO {

    private String name;

    private String description;

    @TableLogic
    private String deleted = "N";
}
