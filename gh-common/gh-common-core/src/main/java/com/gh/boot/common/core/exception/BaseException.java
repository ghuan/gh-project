package com.gh.boot.common.core.exception;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.gh.boot.common.core.enums.LogLevelEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc: 基础异常类
 * @author: tianma
 * @date: 2023/4/27
 */
@Getter
@Slf4j
public class BaseException extends RuntimeException {

    /**
     * 日志级别 默认 error
     */
    private LogLevelEnum logLevelEnum;

    /**
     * 响应枚举 默认 500
     */
    private IExceptionEnum exceptionEnum;

    public BaseException() {
        super(ExceptionCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message,Object... args) {
        super(StrUtil.format(message,args));
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }
    public BaseException(Throwable cause) {
        super(cause);
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(LogLevelEnum logLevelEnum){
        super();
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(LogLevelEnum logLevelEnum, String message, Object... args) {
        super(StrUtil.format(message,args));
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(LogLevelEnum logLevelEnum, String message, Throwable cause) {
        super(message, cause);
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }
    public BaseException(LogLevelEnum logLevelEnum, Throwable cause) {
        super(cause);
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = ExceptionCodeEnum.INTERNAL_SERVER_ERROR;
    }

    public BaseException(IExceptionEnum exceptionEnum){
        super();
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, String message, Object... args) {
        super(StrUtil.format(message,args));
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, String message, Throwable cause) {
        super(message, cause);
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = exceptionEnum;
    }
    public BaseException(IExceptionEnum exceptionEnum, Throwable cause) {
        super(cause);
        this.logLevelEnum = LogLevelEnum.ERROR;
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum){
        super();
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Object... args) {
        super(StrUtil.format(message,args));
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, String message, Throwable cause) {
        super(message, cause);
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = exceptionEnum;
    }
    public BaseException(IExceptionEnum exceptionEnum, LogLevelEnum logLevelEnum, Throwable cause) {
        super(cause);
        this.logLevelEnum = logLevelEnum;
        this.exceptionEnum = exceptionEnum;
    }

    /**
     * @desc 打印日志
     * @date 2023/4/27 17:26
     * @author tianma
     */
    public void log(){
        log(String.format("errorCode: %s, errorMessage: %s", exceptionEnum.getCode(), this.getMessage()));
    }

    /**
     * @desc 打印日志
     * @date 2023/4/27 17:26
     * @author tianma
     */
    public void log(String message,Object... args){
        if(ArrayUtil.isEmpty(args)){
            log(message,this);
            return;
        }
        switch (logLevelEnum){
            case INFO:
                log.info(message,args);
                break;
            case WARN:
                log.warn(message,args);
                break;
            case ERROR:
                log.error(message,args);
                break;
            default:
                log.error(message,args);
                break;
        }
    }
}
