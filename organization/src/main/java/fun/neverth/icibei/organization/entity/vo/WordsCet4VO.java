package fun.neverth.icibei.organization.entity.vo;

import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neverth.li
 * @date 2020/10/19 17:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WordsCet4VO extends BasePO {
    private String word;

    private String sw;

    private String phonetic;

    private String definition;

    private String translation;

    private String pos;

    private String collins;

    private String oxford;

    private String tag;

    private String bnc;

    private String frq;

    private String exchange;

    private String detail;

    private String audio;

    /**
     * 从百度翻译爬取的内容
     */
    private Object baidu;
}
