package com.gh.common.core.exception;

/**
 * @desc: 异常响应接口类
 * @author: tianma
 * @date: 2023/4/27
 */
public interface IExceptionEnum {

    /**
     * 获取响应码
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取响应信息
     *
     * @return
     */
    String getMessage();
}
