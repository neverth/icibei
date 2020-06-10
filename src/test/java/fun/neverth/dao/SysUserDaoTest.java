package fun.neverth.dao;

import fun.neverth.bean.po.SysUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class SysUserDaoTest {
    @Test
    public void selectAllSysUserTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        SysUserDao sysUserDao= act.getBean(SysUserDao.class);
        ArrayList<SysUser> sysUserArrayList = sysUserDao.selectAllSysUser();
        sysUserArrayList.forEach(System.out::println);
    }
    @Test
    public void selectSysUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        SysUserDao sysUserDao= act.getBean(SysUserDao.class);
        SysUser sysUser = new SysUser();
        sysUser.setSys_user_id(20);
        sysUser.setSys_user_account("1@1");
        SysUser sysUser1 = sysUserDao.selectSysUserById(sysUser);
        SysUser sysUser2 = sysUserDao.selectSysUserByAccount(sysUser);
        System.out.println(sysUser1);
        System.out.println(sysUser2);
    }


    @Test
    public void insertSysUserTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        SysUserDao sysUserDao= act.getBean(SysUserDao.class);
        SysUser sysUser = new SysUser();
        sysUser.setSys_user_account("999@1");
        System.out.println(sysUserDao.insertSysUser(sysUser));
        System.out.println(sysUser.getSys_user_id());
    }
    @Test
    public void deleteSysUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        SysUserDao sysUserDao= act.getBean(SysUserDao.class);
        SysUser sysUser = new SysUser();
        sysUser.setSys_user_id(9);
        sysUser.setSys_user_account("9999@1");
        System.out.println(sysUserDao.deleteSysUserByAccount(sysUser));
        System.out.println(sysUserDao.deleteSysUserById(sysUser));

    }

    @Test
    public void updateSysUserByIdOrAccountTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        SysUserDao sysUserDao= act.getBean(SysUserDao.class);
        SysUser sysUser = new SysUser();
        sysUser.setSys_user_id(13);
        SysUser sysUser1 = new SysUser();
        sysUser1.setSys_user_id(13);
//        System.out.println(sysUserDao.updateSysUserById(sysUser, sysUser1));
//        System.out.println(sysUserDao.updateSysUserByAccount(sysUser, sysUser1));
    }

}
