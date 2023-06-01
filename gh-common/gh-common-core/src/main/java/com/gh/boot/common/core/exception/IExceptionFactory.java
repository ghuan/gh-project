package com.gh.boot.common.core.exception;


import com.gh.boot.common.core.enums.LogLevelEnum;

/**
 * @desc 异常工厂接口类
 * @date 2023/4/27 15:18
 * @author tianma
 */
public interface IExceptionFactory {

    /**
     * 创建异常
     */
    BaseException newException();


    /**
     * 创建异常
     */
    BaseException newException(String message,Object... args);

    /**
     * 创建异常
     */
    BaseException newException(String message, Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(LogLevelEnum logLevelEnum);


    /**
     * 创建异常
     */
    BaseException newException(LogLevelEnum logLevelEnum, String message, Object... args);

    /**
     * 创建异常
     */
    BaseException newException(LogLevelEnum logLevelEnum, String message, Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(LogLevelEnum logLevelEnum, Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum);


    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum,String message,Object... args);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum,String message, Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum,Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum);


    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause);

    /**
     * 创建异常
     */
    BaseException newException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause);
}
