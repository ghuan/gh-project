package com.gh.boot.common.core.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * @desc 日志级别枚举
 * @date 2023/4/27 10:20
 * @author tianma
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum LogLevelEnum {

    DEBUG("debug", "DEBUG"),
    INFO("info", "INFO"),
    WARN("warn", "WARN"),
    ERROR("error", "ERROR");

    private String code;
    private String value;
}
