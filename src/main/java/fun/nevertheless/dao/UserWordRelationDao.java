package fun.nevertheless.dao;

import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.po.WordCet4;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface UserWordRelationDao {
    // 查
    public ArrayList<UserWordRelation> selectAllUserWordRelation();
    public UserWordRelation selectUserWordRelationById(@Param("userWordRelation")UserWordRelation userWordRelation);
    public ArrayList<UserWordRelation> selectAllUserWordRelationByUserId(@Param("user")User user);
    public ArrayList<UserWordRelation> selectAllUserWordRelationByWordId(@Param("wordCet4") WordCet4 wordCet4);
    public ArrayList<UserWordRelation> selectAllUserWordLikeRelationByUserId(@Param("user")User user);


    // 插
    public int insertUserWordRelation(@Param("userWordRelation")UserWordRelation userWordRelation);

    // 删
    public int deleteUserById(@Param("user") User user);
    public int deleteUserByAccount(@Param("user")User user);

    // 改
    public int updateRelationById(@Param("userWordRelation")UserWordRelation userWordRelation);
    public int updateUserByAccount(@Param("user")User user, @Param("user1")User user1);
}
