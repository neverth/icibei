package fun.neverth.service;

import fun.neverth.bean.po.User;
import fun.neverth.bean.po.UserWordRelation;
import org.json.JSONObject;


public interface UserWordRelationService {
    public int updateRelationById(UserWordRelation userWordRelation, int type);
    public JSONObject getWordUserAndRelationListByUser(int pageNum, int pageSize, User user);
    public int insertUserWordRelation(UserWordRelation userWordRelation);
}
