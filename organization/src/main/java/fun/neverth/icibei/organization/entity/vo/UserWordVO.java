package fun.neverth.icibei.organization.entity.vo;

import fun.neverth.icibei.common.web.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author NeverTh
 * @date 0:42 2020/10/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserWordVO extends BaseVO {
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
