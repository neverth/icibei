package fun.neverth.dao;

import fun.neverth.bean.po.User;
import fun.neverth.bean.po.UserWordRelation;
import fun.neverth.bean.po.WordCet4;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class UserWordRelationDaoTest {
    @Test
    public void selectAllUserWordRelationTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        ArrayList<UserWordRelation> userWordRelationArrayList = userWordRelationDao.selectAllUserWordRelation();
        userWordRelationArrayList.forEach(System.out::println);
    }
    @Test
    public void selectUserWordRelationByIdTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        UserWordRelation userWordRelation = new UserWordRelation();
        userWordRelation.setUser_word_relation_id(1);
        UserWordRelation userWordRelationArrayList = userWordRelationDao.selectUserWordRelationById(userWordRelation);
        System.out.println(userWordRelationArrayList);
    }

    @Test
    public void selectAllUserWordRelationByUserIdTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        User user = new User();
        user.setUser_id(1);
        ArrayList<UserWordRelation> userWordRelationArrayList = userWordRelationDao.selectAllUserWordRelationByUserId(user);
        userWordRelationArrayList.forEach(System.out::println);
    }

    @Test
    public void selectAllUserWordRelationByWordIdTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setId(82678);
        ArrayList<UserWordRelation> userWordRelationArrayList = userWordRelationDao.selectAllUserWordRelationByWordId(wordCet4);
        userWordRelationArrayList.forEach(System.out::println);
    }

    @Test
    public void insertUserWordRelationTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        UserWordRelation userWordRelation = new UserWordRelation();
        userWordRelation.setUser_id(1);
        userWordRelation.setWord_cet4_id(125441);
        int userWordRelationArrayList = userWordRelationDao.insertUserWordRelation(userWordRelation);
        System.out.println(userWordRelation.getUser_word_relation_id());
    }
    @Test
    public void updateRelationByIdTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserWordRelationDao userWordRelationDao = act.getBean(UserWordRelationDao.class);
        UserWordRelation userWordRelation = new UserWordRelation();
        userWordRelation.setUser_word_relation_id(8);
        userWordRelation.setUser_word_relation_is_like(1);
        int userWordRelationArrayList = userWordRelationDao.updateRelationById(userWordRelation);
        System.out.println(userWordRelation.getUser_word_relation_id());
    }


}
