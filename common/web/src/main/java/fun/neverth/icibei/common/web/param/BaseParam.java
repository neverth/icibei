package fun.neverth.icibei.common.web.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.neverth.icibei.common.web.po.BasePO;
import lombok.Data;

import java.util.Date;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 16:07
 */
@Data
public class BaseParam<T extends BasePO> {
    private Date createdTimeStart;
    private Date createdTimeEnd;

    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time", this.createdTimeStart)
                .le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }
}
