package fun.neverth.icibei.common.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:09
 */
@Data
@NoArgsConstructor
public class BaseVO {

    public <T> T toPO(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
