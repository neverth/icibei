package fun.neverth.icibei.common.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fun.neverth.icibei.common.core.exception.BaseException;
import fun.neverth.icibei.common.core.exception.ErrorType;
import fun.neverth.icibei.common.core.exception.SystemErrorType;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/11 23:06
 */
@Data
public class Result<T> {
    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    private String code;

    private String message;

    private final Instant time;
    /**
     * 为null就不序列化
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }


    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    public static Result success() {
        return success(null);
    }


    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }


    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }


    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }


    public static Result fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
