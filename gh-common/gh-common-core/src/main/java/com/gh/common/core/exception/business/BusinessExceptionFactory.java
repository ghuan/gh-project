package com.gh.common.core.exception.business;

import com.gh.common.core.enums.LogLevelEnum;
import com.gh.common.core.exception.BaseException;
import com.gh.common.core.exception.IExceptionEnum;
import com.gh.common.core.exception.IExceptionFactory;

/**
 * @desc: 业务异常工厂类
 * @author: tianma
 * @date: 2023/4/27
 */
public class BusinessExceptionFactory implements IExceptionFactory {
    @Override
    public BaseException newException() {
        return new BusinessException();
    }
    @Override
    public BaseException newException(String message, Object... args) {
        return new BusinessException(message,args);
    }

    @Override
    public BaseException newException(String message, Throwable cause) {
        return new BusinessException(message,cause);
    }

    @Override
    public BaseException newException(Throwable cause) {
        return new BusinessException(cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum) {
        return new BusinessException(logLevelEnum);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Object... args) {
        return new BusinessException(logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new BusinessException(logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, Throwable cause) {
        return new BusinessException(logLevelEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum) {
        return new BusinessException(exceptionEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Object... args) {
        return new BusinessException(exceptionEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Throwable cause) {
        return new BusinessException(exceptionEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, Throwable cause) {
        return new BusinessException(exceptionEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum) {
        return new BusinessException(exceptionEnum, logLevelEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args) {
        return new BusinessException(exceptionEnum, logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new BusinessException(exceptionEnum, logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause) {
        return new BusinessException(exceptionEnum, logLevelEnum,cause);
    }

}
