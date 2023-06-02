package com.gh.common.knife4j.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.gh.common.knife4j.properties.MyKnife4jProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @desc swagger配置
 * @date 2023/4/20 16:08
 * @author tianma
 */
@Slf4j
//@Configuration
//@ConditionalOnWebApplication
public class SwaggerConfig {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Resource
    private MyKnife4jProperties myKnife4JProperties;

    @Bean
    @ConditionalOnMissingBean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        return GroupedOpenApi.builder().group("all")
                .pathsToMatch(paths).build();
    }
    @Bean
    @ConditionalOnMissingBean
    public OpenAPI customOpenAPI() {
        String title = Convert.toStr(myKnife4JProperties.getTitle(), StrUtil.isEmpty(applicationName)?null:applicationName+" API文档");
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version("1.0")
                        .description(Convert.toStr(myKnife4JProperties.getDescription(),title))
                        .termsOfService(Convert.toStr(myKnife4JProperties.getTermsOfService(),"暂无")));
    }
}
