package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户单词信息表
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_word_relation")
public class UserWord extends BasePO {

    /**
     * 用户id，外键user表id
     */
    private Long userId;

    /**
     * 单词id，可选
     */
    private Long wordId;

    /**
     * 单词
     */
    private String word;

    /**
     * 用户跟单词关系,0默认
     */
    private Integer relation;

    /**
     * 练习次数
     */
    private Long exeTimes;

}
