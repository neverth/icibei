package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.neverth.icibei.organization.entity.po.WordDetail;
import fun.neverth.icibei.organization.dao.WordDetailMapper;
import fun.neverth.icibei.organization.service.WordDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@Service
public class WordDetailServiceImpl extends ServiceImpl<WordDetailMapper, WordDetail> implements WordDetailService {
    @Override
    public WordDetail getByWord(String word) {
        QueryWrapper<WordDetail> query = new QueryWrapper<>();
        query.eq("word", word);
        return this.getOne(query);
    }

    @Override
    public List<WordDetail> getByWordList(List<String> words){
        QueryWrapper<WordDetail> query = new QueryWrapper<>();
        query.in("word", words);
        return this.list(query);
    }
}
