package fun.neverth.icibei.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.neverth.icibei.organization.dao.UserWordMapper;
import fun.neverth.icibei.organization.entity.form.UserWordForm;
import fun.neverth.icibei.organization.entity.po.UserWord;
import fun.neverth.icibei.organization.service.UserWordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户单词信息表 服务实现类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@Service
public class UserWordServiceImpl extends ServiceImpl<UserWordMapper, UserWord> implements UserWordService {

    @Override
    public boolean add(UserWordForm form) {
        UserWord userWord = form.toPO(UserWord.class);
        return this.save(userWord);
    }

    @Override
    public boolean incrementExeTimes(String userId, String word) {
        UserWord one = this.get(userId, word);
        if (one == null) {
            one = new UserWord();
            one.setUserId(Long.parseLong(userId));
            one.setWord(word);
            one.setExeTimes(1L);
            return this.save(one);
        }
        one.setExeTimes(one.getExeTimes() + 1);
        return this.updateById(one);
    }

    @Override
    public boolean updateRelation(String userId, String word, int relation) {
        UserWord one = this.get(userId, word);
        one.setRelation(relation);
        return this.updateById(one);
    }

    @Override
    public UserWord get(String userId, String word) {
        QueryWrapper<UserWord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("word", word);
        return this.getOne(queryWrapper);
    }
}
