package fun.neverth.dao;

import fun.neverth.bean.po.WordCet4;
import fun.neverth.common.utils.DownLoadFromUrl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class wordCet4DaoTest {
    @Test
    public void main(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        WordCet4Dao wordCet4List= act.getBean(WordCet4Dao.class);
        DownLoadFromUrl downLoadFromUrl = new DownLoadFromUrl();
        ArrayList<WordCet4> article = wordCet4List.selectAllCet4Words();
        for(WordCet4 wordCet4: article){
            String url = "https://fanyi.baidu.com/gettts?lan=en&text=" + wordCet4.getWord() + "&spd=3&source=web";
            String result =  downLoadFromUrl.downLoadFromUrl(url,
                    wordCet4.getWord() + ".mp3", "C:\\Users\\HUSHUHUA\\Desktop\\workPlace\\icibei\\src\\main\\webapp\\resource\\mp3");
            System.out.println(result);
        }


        article.forEach(System.out::println);
    }

    @Test
    public void selectCet4WordByIdTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        WordCet4Dao wordCet4List= act.getBean(WordCet4Dao.class);
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setId(117689);
        System.out.println(wordCet4List.selectCet4WordById(wordCet4));
    }

    @Test
    public void selectCet4WordByWordTest(){
        ApplicationContext act =
                new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        WordCet4Dao wordCet4List= act.getBean(WordCet4Dao.class);
        WordCet4 wordCet4 = new WordCet4();
        wordCet4.setWord("test");
        System.out.println(wordCet4List.selectCet4WordByWord(wordCet4));
    }
}
