package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.param.WordsCet4QueryParam;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.entity.vo.WordsCet4Page;
import fun.neverth.icibei.organization.entity.vo.WordsCet4VO;

import java.util.List;

/**
 * @author neverth.li
 * @date 2020/10/10 16:07
 */
public interface WordsCet4Service {

    WordsCet4 getByWord(String word);

    WordsCet4 get(String id);

    boolean add(WordsCet4 wordsCet4);

    boolean update(WordsCet4 wordsCet4);

    boolean delete(String id);

    List<WordsCet4VO> getFromWords(String[] words);

    WordsCet4Page query(WordsCet4QueryParam param);

    List<WordsCet4VO> getWordDatasRandom(int pageSize);

    List<String> getWordsRandom(int pageSize);

}
