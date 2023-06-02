package com.gh.common.core.exception.argument;


import com.gh.common.core.enums.LogLevelEnum;
import com.gh.common.core.exception.BaseException;
import com.gh.common.core.exception.IExceptionEnum;

/**
 * @desc 参数异常
 * @date 2023/4/27 11:10
 * @author tianma
 */
public class ArgumentException extends BaseException {

    public ArgumentException() {
        super();
    }

    public ArgumentException(String message, Object... args) {
        super(message,args);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    public ArgumentException(Throwable cause) {
        super(cause);
    }

    public ArgumentException(LogLevelEnum logLevelEnum){
        super(logLevelEnum);
    }

    public ArgumentException(LogLevelEnum logLevelEnum, String message, Object... args) {
        super(logLevelEnum,message,args);
    }

    public ArgumentException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        super(logLevelEnum,message, cause);
    }
    public ArgumentException(LogLevelEnum logLevelEnum, Throwable cause) {
        super(logLevelEnum,cause);
    }

    public ArgumentException(IExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, String message, Object... args){
        super(exceptionEnum,message,args);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, String message, Throwable cause){
        super(exceptionEnum,message,cause);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, Throwable cause){
        super(exceptionEnum,cause);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum){
        super(exceptionEnum, logLevelEnum);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args){
        super(exceptionEnum, logLevelEnum,message,args);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause){
        super(exceptionEnum, logLevelEnum,message,cause);
    }

    public ArgumentException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause){
        super(exceptionEnum, logLevelEnum,cause);
    }
}
