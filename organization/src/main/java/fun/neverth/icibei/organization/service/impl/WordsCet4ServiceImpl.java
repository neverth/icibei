package fun.neverth.icibei.organization.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.WordsCet4Mapper;
import fun.neverth.icibei.organization.entity.param.WordsCet4QueryParam;
import fun.neverth.icibei.organization.entity.po.WordDetail;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.entity.vo.WordsCet4Page;
import fun.neverth.icibei.organization.entity.vo.WordsCet4VO;
import fun.neverth.icibei.organization.service.WordDetailService;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author neverth.li
 * @date 2020/10/10 16:13
 */
@Service
public class WordsCet4ServiceImpl extends ServiceImpl<WordsCet4Mapper, WordsCet4> implements WordsCet4Service {

    @Autowired
    private WordsCet4Mapper wordsCet4Mapper;

    @Autowired
    private WordDetailService wordDetailService;

    @Override
    public WordsCet4 get(String id) {
         return this.getById(id);
    }

    @Override
    public boolean add(WordsCet4 wordsCet4) {
        return this.save(wordsCet4);
    }

    @Override
    public boolean update(WordsCet4 wordsCet4) {
        return this.updateById(wordsCet4);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public WordsCet4Page query(WordsCet4QueryParam param) {

        if (Objects.isNull(param.getUserId())){
            QueryWrapper<WordsCet4> queryWrapper =  new QueryWrapper<>();
            queryWrapper.orderByAsc("word");
            IPage<WordsCet4> page = new Page<>(param.getCurrent(), param.getSize());
            page = wordsCet4Mapper.selectPage(page, queryWrapper);
            List<WordsCet4> records = page.getRecords();

            WordsCet4Page wordsCet4Page = new WordsCet4Page();
            List<WordsCet4VO> wordsCet4VOList = new ArrayList<>();
            List<String> wordList = new ArrayList<>();

            for (WordsCet4 record : records) {
                WordsCet4VO wordsCet4VO = new WordsCet4VO();
                BeanUtils.copyProperties(record, wordsCet4VO);
                wordsCet4VOList.add(wordsCet4VO);
                wordList.add(record.getWord());
            }

            List<WordDetail> wordDetailList = wordDetailService.getByWordList(wordList);
            for (WordsCet4VO wordsCet4VO : wordsCet4VOList) {
                for (WordDetail wordDetail : wordDetailList) {
                    if (StringUtils.equals(wordsCet4VO.getWord(), wordDetail.getWord())){
                        wordsCet4VO.setBaidu(JSON.parseObject( wordDetail.getBaidu()));
                        break;
                    }
                }
            }
            wordsCet4Page.setWordsCet4List(wordsCet4VOList);
            wordsCet4Page.setPercentage(param.getPercentage());
            BeanUtils.copyProperties(page, wordsCet4Page);
            return wordsCet4Page;
        }
        return null;
    }

    @Override
    public WordsCet4 getByWord(String word) {
        QueryWrapper<WordsCet4> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("word", word);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<WordsCet4VO> getFromWords(String[] words) {
        List<WordsCet4VO> res = new ArrayList<>();
        QueryWrapper<WordsCet4> queryWrapper =  new QueryWrapper<>();
        queryWrapper.in("word", Arrays.asList(words));
        List<WordsCet4> wordsCet4s = wordsCet4Mapper.selectList(queryWrapper);

        for (WordsCet4 wordsCet4 : wordsCet4s) {
            WordsCet4VO wordsCet4VO = new WordsCet4VO();
            BeanUtils.copyProperties(wordsCet4, wordsCet4VO);
            res.add(wordsCet4VO);
        }

        // 填充百度翻译信息
        List<WordDetail> wordDetailList = wordDetailService.getByWordList(Arrays.asList(words));
        for (WordsCet4VO w : res) {
            for (WordDetail wordDetail : wordDetailList) {
                if (w.getWord().equals(wordDetail.getWord())){
                    w.setBaidu(JSON.parseObject( wordDetail.getBaidu()));
                    break;
                }
            }
        }

        return res;
    }
}
