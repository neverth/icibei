package fun.neverth.bean.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class WordCet4 implements Serializable {
    private static final long serialVersionUID = 5855999408221091483L;
    private int id;
    private String word;
    private String phonetic;
    private String definition;
    private String translation;
    private String pos;
    private String collins;
    private String oxford;
    private String tag;
    private int bnc;
    private int frq;
    private String exchange;
    private String detail;
    private String audio;
}
