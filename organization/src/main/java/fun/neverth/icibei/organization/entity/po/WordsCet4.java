package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author neverth.li
 * @date 2020/10/10 16:04
 */
@Data
@TableName("words_cet4")
public class WordsCet4 {
    @TableId(type = IdType.AUTO)
    private String id;

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
}
