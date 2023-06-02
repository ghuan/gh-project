package com.gh.common.core.annotation.async;


import com.gh.common.core.config.AsyncPoolConfig;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

/**
 * 自定义异步线程池配置启用
 * @author tianma
 * @date 13:14 2020/10/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@EnableAsync
@Import(AsyncPoolConfig.class)
public @interface EnableGhAsync {

}
