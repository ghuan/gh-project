package com.gh.boot.common.web.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>返回结果</p>
 *
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    OK(200, "成功"),
    INTERNAL_SERVER_ERROR(500, "系统异常"),
    BAD_REQUEST(400,"参数不合法"),
    UNAUTHORIZED(401,"无权限"),
    FORBIDDEN(403,"拒绝访问"),
    NOT_FOUND(404, "未找到资源"),
    METHOD_NOT_ALLOWED(405, "方法不支持"),
    ILLEGAL_ACCESS(406, "非法获取"),
    FILE_TOO_LARGE(6014, "文件大小超出限制, 请压缩或降低文件质量."),
    DATA_INTEGRITY_VIOLATION(6022, "数据库校验异常"),
    DUPLICATE_KEY_EXCEPTION(6023, "数据库主键冲突异常"),
    OPENFEIGN_FAILED(8400, "feign接口调用失败"),
    DATA_CHECK_ERROR(8401,"参数校验未通过: {}"),
    ;

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private final String message;
}
