package fun.neverth.icibei.organization.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author neverTh
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WordTts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String word;

    private byte[] baiduGettts;

    public WordTts(String word, byte[] baiduGettts){
        this.word  = word;
        this.baiduGettts = baiduGettts;
    }

}
