package com.gh.common.knife4j.annotation;

import com.gh.common.knife4j.properties.MyKnife4jProperties;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description: knife4j启动注解类
 * @author huan.gao
 * @date: 2020/10/20 11:17
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(Knife4jProperties.class)
public @interface EnableKnife4j {
}
