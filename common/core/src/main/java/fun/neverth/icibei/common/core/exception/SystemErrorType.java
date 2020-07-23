package fun.neverth.icibei.common.core.exception;

import lombok.Getter;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 15:47
 */
@Getter
public enum SystemErrorType implements ErrorType{

    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    INVALID_TOKEN("020001", "无效token"),
    UPLOAD_FILE_SIZE_LIMIT("020010", "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY("030000","唯一键冲突");

    private String code;
    
    private String message;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.message = mesg;
    }
}
