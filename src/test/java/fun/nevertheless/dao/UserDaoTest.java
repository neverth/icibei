package fun.nevertheless.dao;

import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class UserDaoTest {
    @Test
    public void selectAllUserTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao userDao= act.getBean(UserDao.class);
        ArrayList<User> UserArrayList = userDao.selectAllUser();
        UserArrayList.forEach(System.out::println);
    }
    @Test
    public void selectUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao UserDao= act.getBean(UserDao.class);
        User User = new User();
        User.setUser_id(1);
        User.setUser_account("1234");
        User User1 = UserDao.selectUserById(User);
        User User2 = UserDao.selectUserByAccount(User);
        System.out.println(User1);
        System.out.println(User2);
    }


    @Test
    public void insertUserTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao UserDao= act.getBean(UserDao.class);
        User User = new User();
        for (int i = 0; i < 30; i++){
            User.setUser_account("9@@@1" + i);
            User.setUser_passw("9@@@@@" + i);
            System.out.println(UserDao.insertUser(User));
        }

        System.out.println(User.getUser_id());
    }
    @Test
    public void deleteUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao UserDao= act.getBean(UserDao.class);
        User User = new User();
        User.setUser_id(2);
        User.setUser_account("9999@1");
        System.out.println(UserDao.deleteUserByAccount(User));
        System.out.println(UserDao.deleteUserById(User));

    }

    @Test
    public void updateUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao UserDao= act.getBean(UserDao.class);
        User User = new User();
        User.setUser_id(3);
        User User1 = new User();
        User1.setUser_id(3);
        User1.setUser_passw("3213213");
        System.out.println(UserDao.updateUserById(User, User1));
//        System.out.println(UserDao.updateUserByAccount(User, User1));
    }
}
