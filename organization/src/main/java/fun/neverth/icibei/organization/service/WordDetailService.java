package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.po.WordDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
public interface WordDetailService extends IService<WordDetail> {

    WordDetail getByWord(String word);

    List<WordDetail> getByWordList(List<String> words);

}
