package fun.neverth.icibei.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.neverth.icibei.organization.entity.param.RoleQueryParam;
import fun.neverth.icibei.organization.entity.po.WordsCet4;

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

    IPage<WordsCet4> query(Page<WordsCet4> page, RoleQueryParam roleQueryParam);

}
