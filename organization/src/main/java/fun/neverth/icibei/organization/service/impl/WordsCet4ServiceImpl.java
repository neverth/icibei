package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.WordsCet4Mapper;
import fun.neverth.icibei.organization.entity.param.WordsCet4QueryParam;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.entity.vo.WordsCet4Page;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author neverth.li
 * @date 2020/10/10 16:13
 */
@Service
public class WordsCet4ServiceImpl extends ServiceImpl<WordsCet4Mapper, WordsCet4> implements WordsCet4Service {

    @Autowired
    private WordsCet4Mapper wordsCet4Mapper;

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

            WordsCet4Page wordsCet4Page = new WordsCet4Page();
            wordsCet4Page.setWordsCet4List(page.getRecords());
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
}
