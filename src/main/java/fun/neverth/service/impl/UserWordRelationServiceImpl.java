package fun.neverth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.neverth.bean.po.User;
import fun.neverth.bean.po.UserWordRelation;
import fun.neverth.bean.po.WordCet4;
import fun.neverth.bean.vo.UserWordData;
import fun.neverth.dao.UserWordRelationDao;
import fun.neverth.dao.WordCet4Dao;
import fun.neverth.service.UserWordRelationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserWordRelationServiceImpl implements UserWordRelationService {

    @Autowired
    UserWordRelationDao userWordRelationDao;
    @Autowired
    WordCet4Dao wordCet4Dao;

    @Override
    public int updateRelationById(UserWordRelation userWordRelation, int type) {
        if (type == 1){
            UserWordRelation userWordRelation1 =
                    userWordRelationDao.selectUserWordRelationById(userWordRelation);
            userWordRelation1.setUser_word_relation_is_like(userWordRelation.getUser_word_relation_is_like());
            return userWordRelationDao.updateRelationById(userWordRelation1);
        }
        return 0;
    }

    @Override
    public JSONObject getWordUserAndRelationListByUser(int pageNum, int pageSize, User user) {
        ArrayList<UserWordData> userWordDataArrayList = new ArrayList<>();

        PageHelper.startPage(pageNum, pageSize);
        ArrayList<WordCet4> wordCet4s = wordCet4Dao.selectAllCet4Words();
        ArrayList<UserWordRelation> userWordRelations =
                userWordRelationDao.selectAllUserWordLikeRelationByUserId(user);

        for (WordCet4 wordCet4 : wordCet4s){
            UserWordData userWordData = new UserWordData();
            for (UserWordRelation userWordRelation: userWordRelations){
                if ((wordCet4.getId() == userWordRelation.getWord_cet4_id()) &&
                        userWordRelation.getUser_word_relation_is_like() == 1){
                    userWordData.setUserWordRelation(userWordRelation);
                }
            }
            userWordData.setWordCet4(wordCet4);
            userWordDataArrayList.add(userWordData);
        }

        PageInfo<WordCet4> page = new PageInfo<>(wordCet4s);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userWordData", userWordDataArrayList);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    @Override
    public int insertUserWordRelation(UserWordRelation userWordRelation) {
        return userWordRelationDao.insertUserWordRelation(userWordRelation);
    }

}
