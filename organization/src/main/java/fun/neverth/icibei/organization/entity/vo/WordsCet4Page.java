package fun.neverth.icibei.organization.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author neverth.li
 * @date 2020/10/16 15:55
 */
@Data
public class WordsCet4Page {

    private List<WordsCet4VO> wordsCet4List;
    /**
     * 总数
     */
    private long total;
    /**
     * 每页显示条数，默认 10
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    private double percentage;

}
