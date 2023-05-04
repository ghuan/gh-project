package com.gh.boot.common.core.exception.business;


import com.gh.boot.common.core.enums.LogLevelEnum;
import com.gh.boot.common.core.exception.BaseException;
import com.gh.boot.common.core.exception.IExceptionEnum;

/**
 * @desc 业务异常
 * @date 2023/4/27 11:10
 * @author tianma
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message,Object... args) {
        super(message,args);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(LogLevelEnum logLevelEnum){
        super(logLevelEnum);
    }

    public BusinessException(LogLevelEnum logLevelEnum, String message, Object... args) {
        super(logLevelEnum,message,args);
    }

    public BusinessException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        super(logLevelEnum,message, cause);
    }
    public BusinessException(LogLevelEnum logLevelEnum, Throwable cause) {
        super(logLevelEnum,cause);
    }

    public BusinessException(IExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }

    public BusinessException(IExceptionEnum exceptionEnum,String message,Object... args){
        super(exceptionEnum,message,args);
    }

    public BusinessException(IExceptionEnum exceptionEnum,String message, Throwable cause){
        super(exceptionEnum,message,cause);
    }

    public BusinessException(IExceptionEnum exceptionEnum,Throwable cause){
        super(exceptionEnum,cause);
    }

    public BusinessException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum){
        super(exceptionEnum, logLevelEnum);
    }

    public BusinessException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args){
        super(exceptionEnum, logLevelEnum,message,args);
    }

    public BusinessException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause){
        super(exceptionEnum, logLevelEnum,message,cause);
    }

    public BusinessException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause){
        super(exceptionEnum, logLevelEnum,cause);
    }
}
