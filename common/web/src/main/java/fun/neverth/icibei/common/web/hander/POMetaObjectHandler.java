package fun.neverth.icibei.common.web.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fun.neverth.icibei.common.web.po.BasePO;
import fun.neverth.icibei.common.core.util.UserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 自动填充
 *
 * @author NeverTh
 * @date 2020/7/12 17:05
 */
public class POMetaObjectHandler implements MetaObjectHandler {

    private String getCurrentUsername() {
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePO.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("createdBy", getCurrentUsername(), metaObject);
        this.setInsertFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("updatedBy", getCurrentUsername(), metaObject);
        this.setUpdateFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}
