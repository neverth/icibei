package fun.nevertheless.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.nevertheless.bean.po.User;
import net.sf.jsqlparser.*;
import fun.nevertheless.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class UserServiceTest {
    @Test
    public void test(){

        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        UserDao userDao= act.getBean(UserDao.class);


        PageHelper.startPage(2, 5);
        ArrayList<User> list = userDao.selectAllUser();

        PageInfo<User> page = new PageInfo<User>(list);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
    }
}
