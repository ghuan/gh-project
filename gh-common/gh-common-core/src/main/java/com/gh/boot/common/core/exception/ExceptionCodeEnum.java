package com.gh.boot.common.core.exception;

import com.gh.boot.common.core.enums.LogLevelEnum;
import com.gh.boot.common.core.exception.argument.ArgumentExceptionFactory;
import com.gh.boot.common.core.exception.business.BusinessExceptionFactory;
import com.gh.boot.common.core.exception.validation.ValidationExceptionFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * <p>业务异常枚举</p>
 *
 */
@Getter
@AllArgsConstructor
public enum ExceptionCodeEnum implements IExceptionFactory, IExceptionEnum {
    OK(200, "成功", BusinessExceptionFactory.class),
    BAD_REQUEST(400,"参数不合法", ArgumentExceptionFactory.class),
    UNAUTHORIZED(401,"无权限", BusinessExceptionFactory.class),
    FORBIDDEN(403,"拒绝访问", BusinessExceptionFactory.class),
    NOT_FOUND(404, "未找到资源", BusinessExceptionFactory.class),
    METHOD_NOT_ALLOWED(405, "方法不支持", BusinessExceptionFactory.class),
    ILLEGAL_ACCESS(406, "非法获取", BusinessExceptionFactory.class),
    INTERNAL_SERVER_ERROR(500, "系统异常", BusinessExceptionFactory.class),
    FILE_TOO_LARGE(6014, "文件大小超出限制, 请压缩或降低文件质量.", BusinessExceptionFactory.class),
    DATA_INTEGRITY_VIOLATION(6022, "数据库校验异常", BusinessExceptionFactory.class),
    DUPLICATE_KEY_EXCEPTION(6023, "数据库主键冲突异常", BusinessExceptionFactory.class),
    OPENFEIGN_FAILED(8400, "feign接口调用失败", BusinessExceptionFactory.class),
    DATA_CHECK_ERROR(8401,"参数校验未通过: {}", ValidationExceptionFactory.class),
    ;

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 异常工厂类
     */
    private Class<? extends IExceptionFactory> exceptionFactoryClass;

    @SneakyThrows
    @Override
    public BaseException newException() {
        return this.exceptionFactoryClass.newInstance().newException(this);
    }

    @SneakyThrows
    @Override
    public BaseException newException(String message, Object... args) {
        return this.exceptionFactoryClass.newInstance().newException(this,message,args);
    }

    @SneakyThrows
    @Override
    public BaseException newException(String message, Throwable cause) {
        return this.exceptionFactoryClass.newInstance().newException(this,message,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(Throwable cause) {
       return this.exceptionFactoryClass.newInstance().newException(this,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(LogLevelEnum logLevelEnum) {
       return this.exceptionFactoryClass.newInstance().newException(this,logLevelEnum);
    }

    @SneakyThrows
    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Object... args) {
       return this.exceptionFactoryClass.newInstance().newException(this,logLevelEnum,message,args);
    }

    @SneakyThrows
    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return this.exceptionFactoryClass.newInstance().newException(this,logLevelEnum,message,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, Throwable cause) {
        return this.exceptionFactoryClass.newInstance().newException(this,logLevelEnum,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum) {
        return this.exceptionFactoryClass.newInstance().newException(exceptionEnum);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Object... args) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,message,args);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Throwable cause) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,message,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, Throwable cause) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,logLevelEnum);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,logLevelEnum,message,args);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,logLevelEnum,message,cause);
    }

    @SneakyThrows
    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause) {
       return this.exceptionFactoryClass.newInstance().newException(exceptionEnum,logLevelEnum,cause);
    }
}
