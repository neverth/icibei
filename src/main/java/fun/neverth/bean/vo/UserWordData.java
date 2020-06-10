package fun.neverth.bean.vo;

import fun.neverth.bean.po.User;
import fun.neverth.bean.po.UserWordRelation;
import fun.neverth.bean.po.WordCet4;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserWordData implements Serializable {
    private static final long serialVersionUID = -4598714913093154685L;
    private UserWordRelation userWordRelation;
    private WordCet4 wordCet4;
    private User user;
}
