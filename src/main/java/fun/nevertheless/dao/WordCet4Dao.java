package fun.nevertheless.dao;

import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.po.WordCet4;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WordCet4Dao {
    // 查
    public ArrayList<WordCet4> selectAllCet4Words();
    public WordCet4 selectCet4WordById(@Param("wordCet4")WordCet4 wordCet4);
    public ArrayList<WordCet4> selectCet4WordByWord(@Param("wordCet4")WordCet4 wordCet4);


    // 改
    public int updateWordById(@Param("wordCet4") WordCet4 wordCet4);

    // 插
    public int insertWord(@Param("wordCet4") WordCet4 wordCet4);
}
