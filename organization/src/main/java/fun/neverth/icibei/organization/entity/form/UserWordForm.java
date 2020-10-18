package fun.neverth.icibei.organization.entity.form;

import fun.neverth.icibei.common.web.form.BaseForm;
import fun.neverth.icibei.organization.entity.po.UserWord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author NeverTh
 * @date 0:22 2020/10/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWordForm extends BaseForm<UserWord> {
    /**
     * 用户id，外键user表id
     */
    @NotNull(message = "userId不能为空")
    private Long userId;

    /**
     * 单词id，可选
     */

    private Long wordId;
    /**
     * 单词
     */
    @NotBlank(message = "单词不能为空")
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
