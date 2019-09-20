package fun.nevertheless.service;

import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.vo.UserWordData;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface UserWordRelationService {
    public int updateRelationById(UserWordRelation userWordRelation, int type);
    public JSONObject getWordUserAndRelationListByUser(int pageNum, int pageSize, User user);
    public int insertUserWordRelation(UserWordRelation userWordRelation);
}
