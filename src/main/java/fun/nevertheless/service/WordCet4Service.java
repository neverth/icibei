package fun.nevertheless.service;

import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.po.WordCet4;
import org.json.JSONObject;

import java.util.ArrayList;

public interface WordCet4Service {
    public ArrayList<WordCet4> getUserInitialCet4Words(User user);
    public JSONObject getCet4List(int pageNum, int pageSize);
    public JSONObject selectLikeCet4WordsByUserId(int pageNum, int pageSize, User user);
    public JSONObject getCet4ListrRndom(int total);
    public JSONObject getCet4ListrRndomByUserId(int total, User user);
    public int updateWordById(WordCet4 wordCet4);
    public int insertCet4Word(WordCet4 wordCet4);
    public JSONObject selectCet4WordByWord(WordCet4 wordCet4);

}
