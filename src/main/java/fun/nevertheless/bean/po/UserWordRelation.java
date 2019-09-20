package fun.nevertheless.bean.po;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserWordRelation implements Serializable {
    private static final long serialVersionUID = -3978081710798164026L;
    private int user_word_relation_id;
    private int user_id;
    private int word_cet4_id;
    private int user_word_relation_is_like;
    private int user_word_relation_not_like;
    private int user_word_relation_rapc_times;
}
