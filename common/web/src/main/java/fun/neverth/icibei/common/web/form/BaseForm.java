package fun.neverth.icibei.common.web.form;

import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 15:56
 */
@Slf4j
@Data
public class BaseForm<T extends BasePO> {

    private String username;

    public T toPO(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }

    public T toPO(String id, Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        t.setId(id);
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
