package fun.neverth.icibei.organization.entity.param;

import lombok.Data;

/**
 * @author neverth.li
 * @date 2020/10/10 16:18
 */
@Data
public class WordsCet4QueryParam {
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    private String userId = null;

    /**
     * 喜欢单词和其他单词比例
     */
    private double percentage = 0.5;
}
