package fun.neverth.icibei.organization.service;

import fun.neverth.icibei.organization.entity.param.WordsCet4QueryParam;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.entity.vo.WordsCet4Page;

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

    WordsCet4Page query(WordsCet4QueryParam param);
}
