package fun.nevertheless.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.WordCet4;
import fun.nevertheless.dao.UserDao;
import fun.nevertheless.dao.WordCet4Dao;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class WordCet4ServiceTest {
    @Test
    public void getCet4ListTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        WordCet4Dao wordCet4Dao= act.getBean(WordCet4Dao.class);


        PageHelper.startPage(1, 10);
        ArrayList<WordCet4> list = wordCet4Dao.selectAllCet4Words();

        PageInfo<WordCet4> page = new PageInfo<WordCet4>(list);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
    }
}
