package com.gh.boot.common.core.exception.validation;

import com.gh.boot.common.core.enums.LogLevelEnum;
import com.gh.boot.common.core.exception.BaseException;
import com.gh.boot.common.core.exception.IExceptionEnum;
import com.gh.boot.common.core.exception.IExceptionFactory;

/**
 * @desc: 校验异常工厂类
 * @author: tianma
 * @date: 2023/4/27
 */
public class ValidationExceptionFactory implements IExceptionFactory {
    @Override
    public BaseException newException() {
        return new ValidationException();
    }
    @Override
    public BaseException newException(String message, Object... args) {
        return new ValidationException(message,args);
    }

    @Override
    public BaseException newException(String message, Throwable cause) {
        return new ValidationException(message,cause);
    }

    @Override
    public BaseException newException(Throwable cause) {
        return new ValidationException(cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum) {
        return new ValidationException(logLevelEnum);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Object... args) {
        return new ValidationException(logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new ValidationException(logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, Throwable cause) {
        return new ValidationException(logLevelEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum) {
        return new ValidationException(exceptionEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Object... args) {
        return new ValidationException(exceptionEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Throwable cause) {
        return new ValidationException(exceptionEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, Throwable cause) {
        return new ValidationException(exceptionEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum) {
        return new ValidationException(exceptionEnum, logLevelEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args) {
        return new ValidationException(exceptionEnum, logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new ValidationException(exceptionEnum, logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause) {
        return new ValidationException(exceptionEnum, logLevelEnum,cause);
    }

}
