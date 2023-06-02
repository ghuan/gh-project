package com.gh.common.core.exception.argument;

import com.gh.common.core.enums.LogLevelEnum;
import com.gh.common.core.exception.BaseException;
import com.gh.common.core.exception.IExceptionEnum;
import com.gh.common.core.exception.IExceptionFactory;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/4/27
 */
public class ArgumentExceptionFactory implements IExceptionFactory {
    @Override
    public BaseException newException() {
        return new ArgumentException();
    }
    @Override
    public BaseException newException(String message, Object... args) {
        return new ArgumentException(message,args);
    }

    @Override
    public BaseException newException(String message, Throwable cause) {
        return new ArgumentException(message,cause);
    }

    @Override
    public BaseException newException(Throwable cause) {
        return new ArgumentException(cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum) {
        return new ArgumentException(logLevelEnum);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Object... args) {
        return new ArgumentException(logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new ArgumentException(logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(LogLevelEnum logLevelEnum, Throwable cause) {
        return new ArgumentException(logLevelEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum) {
        return new ArgumentException(exceptionEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Object... args) {
        return new ArgumentException(exceptionEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, String message, Throwable cause) {
        return new ArgumentException(exceptionEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, Throwable cause) {
        return new ArgumentException(exceptionEnum,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum) {
        return new ArgumentException(exceptionEnum, logLevelEnum);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args) {
        return new ArgumentException(exceptionEnum, logLevelEnum,message,args);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause) {
        return new ArgumentException(exceptionEnum, logLevelEnum,message,cause);
    }

    @Override
    public BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause) {
        return new ArgumentException(exceptionEnum, logLevelEnum,cause);
    }

}
