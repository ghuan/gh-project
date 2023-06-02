package com.gh.common.core.exception.validation;


import com.gh.common.core.enums.LogLevelEnum;
import com.gh.common.core.exception.BaseException;
import com.gh.common.core.exception.IExceptionEnum;

/**
 * @desc 校验异常
 * @date 2023/4/27 11:10
 * @author tianma
 */
public class ValidationException extends BaseException {

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Object... args) {
        super(message,args);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(LogLevelEnum logLevelEnum){
        super(logLevelEnum);
    }

    public ValidationException(LogLevelEnum logLevelEnum, String message, Object... args) {
        super(logLevelEnum,message,args);
    }

    public ValidationException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        super(logLevelEnum,message, cause);
    }
    public ValidationException(LogLevelEnum logLevelEnum, Throwable cause) {
        super(logLevelEnum,cause);
    }

    public ValidationException(IExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }

    public ValidationException(IExceptionEnum exceptionEnum, String message, Object... args){
        super(exceptionEnum,message,args);
    }

    public ValidationException(IExceptionEnum exceptionEnum, String message, Throwable cause){
        super(exceptionEnum,message,cause);
    }

    public ValidationException(IExceptionEnum exceptionEnum, Throwable cause){
        super(exceptionEnum,cause);
    }

    public ValidationException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum){
        super(exceptionEnum, logLevelEnum);
    }

    public ValidationException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args){
        super(exceptionEnum, logLevelEnum,message,args);
    }

    public ValidationException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause){
        super(exceptionEnum, logLevelEnum,message,cause);
    }

    public ValidationException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause){
        super(exceptionEnum, logLevelEnum,cause);
    }
}
