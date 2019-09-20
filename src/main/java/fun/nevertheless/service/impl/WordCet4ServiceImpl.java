package fun.nevertheless.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.bean.po.WordCet4;
import fun.nevertheless.bean.vo.UserWordData;
import fun.nevertheless.common.utils.DownLoadFromUrl;
import fun.nevertheless.dao.UserWordRelationDao;
import fun.nevertheless.dao.WordCet4Dao;
import fun.nevertheless.service.WordCet4Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

@Service
public class WordCet4ServiceImpl implements WordCet4Service {

    @Autowired
    private WordCet4Dao wordCet4Dao;

    @Autowired
    private UserWordRelationDao userWordRelationDao;

    @Override
    public ArrayList<WordCet4> getUserInitialCet4Words(User user) {
        ArrayList<WordCet4> wordCet4ArrayList = wordCet4Dao.selectAllCet4Words();
        return wordCet4ArrayList;
    }

    @Override
    public JSONObject getCet4List(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<WordCet4> list = wordCet4Dao.selectAllCet4Words();
//        Runnable r = () -> {
//            DownLoadFromUrl downLoadFromUrl = new DownLoadFromUrl();
//            for (WordCet4 wordCet4 : list) {
//                String res = downLoadFromUrl.downLoadFromUrl(
//                        "https://fanyi.baidu.com/gettts?lan=en&text=" + wordCet4.getWord() + "&spd=3&source=web",
//                        wordCet4.getWord() + ".mp3",
//                        "C:\\Users\\HUSHUHUA\\Desktop\\workPlace\\icibei\\src\\main\\webapp\\resource\\mp3");
//                System.out.println(res);
//            }
//        };
//        new Thread(r).start();
        PageInfo<WordCet4> page = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    @Override
    public JSONObject selectLikeCet4WordsByUserId(int pageNum, int pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<UserWordRelation> userWordRelations =
                userWordRelationDao.selectAllUserWordLikeRelationByUserId(user);

        ArrayList<WordCet4> wordCet4ArrayList = new ArrayList<>();
        ArrayList<UserWordData> userWordDataArrayList = new ArrayList<>();


        WordCet4 wordCet4 = new WordCet4();

        for (UserWordRelation userWordRelation : userWordRelations) {
            // 字段不是基本元素
            UserWordData userWordData = new UserWordData();

            wordCet4.setId(userWordRelation.getWord_cet4_id());

            userWordData.setUserWordRelation(userWordRelation);
            userWordData.setWordCet4(wordCet4Dao.selectCet4WordById(wordCet4));

            userWordDataArrayList.add(userWordData);
        }

        PageInfo<UserWordRelation> page = new PageInfo<>(userWordRelations);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userWordData", userWordDataArrayList);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    @Override
    public JSONObject getCet4ListrRndom(int total) {
        ArrayList<WordCet4> list = new ArrayList<>();

        Random rand = new Random();
        // 320
        for (int i = 0; i < total; i++) {
            PageHelper.startPage(rand.nextInt(320) + 1, 10);
            list.addAll(wordCet4Dao.selectAllCet4Words());
        }

//        Runnable r = () -> {
//            DownLoadFromUrl downLoadFromUrl = new DownLoadFromUrl();
//            for (WordCet4 wordCet4 : list) {
//                String res = downLoadFromUrl.downLoadFromUrl(
//                        "https://fanyi.baidu.com/gettts?lan=en&text=" + wordCet4.getWord() + "&spd=3&source=web",
//                        wordCet4.getWord() + ".mp3",
//                        "C:\\Users\\HUSHUHUA\\Desktop\\workPlace\\icibei\\src\\main\\webapp\\resource\\mp3");
//                System.out.println(res);
//            }
//        };
//        new Thread(r).start();
        PageInfo<WordCet4> page = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    @Override
    public JSONObject getCet4ListrRndomByUserId(int total, User user) {
        LinkedHashSet<Integer> randSet = new LinkedHashSet<>();
        ArrayList<WordCet4> list = new ArrayList<>();
        Random rand = new Random();

        // 当用户登录时，选取0.6的未收藏单词，0.4的收藏单词
        PageHelper.startPage(1, 10);
        PageInfo<WordCet4> wordPage = new PageInfo<>(wordCet4Dao.selectAllCet4Words());
        PageHelper.startPage(1, 10);
        PageInfo<UserWordRelation> relationPage = new PageInfo<>(userWordRelationDao.selectAllUserWordLikeRelationByUserId(user));

        int wordTimes, relationTimes;
        wordTimes = new Double(total * 0.6).intValue();
        relationTimes = new Double(total * 0.4).intValue();
        if (relationPage.getPages() < relationTimes) {
            wordTimes += (relationTimes - relationPage.getPages());
        }

        while (randSet.size() != wordTimes) {
            randSet.add(rand.nextInt(wordPage.getPages()) + 1);
        }
        while (randSet.size() != total) {
            randSet.add(rand.nextInt(relationPage.getPages()) + 1);
        }
        int j = 0;
        WordCet4 wordCet4 = new WordCet4();
        for (Integer temp : randSet) {
            if (j > relationTimes) {// 已收藏
                PageHelper.startPage(temp, 10);
                for (UserWordRelation userWordRelation :
                        userWordRelationDao.selectAllUserWordLikeRelationByUserId(user))
                {
                    wordCet4.setId(userWordRelation.getWord_cet4_id());
                    list.add(wordCet4Dao.selectCet4WordById(wordCet4));
                }
            }else{// 未收藏
                PageHelper.startPage(temp, 10);
                list.addAll(wordCet4Dao.selectAllCet4Words());
            }
            j++;
        }

//        Runnable r = () -> {
//            DownLoadFromUrl downLoadFromUrl = new DownLoadFromUrl();
//            for (WordCet4 wordCet41 : list) {
//                String res = downLoadFromUrl.downLoadFromUrl(
//                        "https://fanyi.baidu.com/gettts?lan=en&text=" + wordCet41.getWord() + "&spd=3&source=web",
//                        wordCet41.getWord() + ".mp3",
//                        "C:\\Users\\HUSHUHUA\\Desktop\\workPlace\\icibei\\src\\main\\webapp\\resource\\mp3");
//                System.out.println(res);
//            }
//        };

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        jsonObject.put("totalList", wordPage.getTotal());
        jsonObject.put("pageNum", wordPage.getPageNum());
        jsonObject.put("totalPage", wordPage.getPages());
        return jsonObject;
    }

    @Override
    public int updateWordById(WordCet4 wordCet4) {
        return wordCet4Dao.updateWordById(wordCet4);
    }

    @Override
    public int insertCet4Word(WordCet4 wordCet4) {
        return wordCet4Dao.insertWord(wordCet4);
    }

    @Override
    public JSONObject selectCet4WordByWord(WordCet4 wordCet4) {
        PageHelper.startPage(1, 50);
        ArrayList<WordCet4> list = wordCet4Dao.selectCet4WordByWord(wordCet4);
        PageInfo<WordCet4> page = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        jsonObject.put("totalList", page.getTotal());
        jsonObject.put("pageNum", page.getPageNum());
        jsonObject.put("totalPage", page.getPages());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}
