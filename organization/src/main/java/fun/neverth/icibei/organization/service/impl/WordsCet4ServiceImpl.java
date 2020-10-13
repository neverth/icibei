package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.WordsCet4Mapper;
import fun.neverth.icibei.organization.entity.param.RoleQueryParam;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author neverth.li
 * @date 2020/10/10 16:13
 */
@Service
public class WordsCet4ServiceImpl extends ServiceImpl<WordsCet4Mapper, WordsCet4> implements WordsCet4Service {

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
    public IPage<WordsCet4> query(Page<WordsCet4> page, RoleQueryParam roleQueryParam) {
        return null;
    }

    @Override
    public WordsCet4 getByWord(String word) {
        QueryWrapper<WordsCet4> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("word", word);
        return this.getOne(queryWrapper);
    }
}
