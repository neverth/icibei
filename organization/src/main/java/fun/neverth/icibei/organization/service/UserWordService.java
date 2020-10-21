package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.form.UserWordForm;
import fun.neverth.icibei.organization.entity.po.UserWord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户单词信息表 服务类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
public interface UserWordService extends IService<UserWord> {
    boolean add(UserWordForm form);

    boolean incrementExeTimes(String userId, String word);

    boolean updateRelation(String userId, String word, int relation);

    UserWord get(String userId, String word);
}
