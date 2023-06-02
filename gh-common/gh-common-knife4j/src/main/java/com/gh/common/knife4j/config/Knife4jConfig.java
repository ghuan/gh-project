package com.gh.common.knife4j.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.gh.common.knife4j.properties.GhKnife4jProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @desc swagger配置
 * @date 2023/4/20 16:08
 * @author tianma
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({GhKnife4jProperties.class})
@ConditionalOnProperty(
        name = {"knife4j.enable"},
        matchIfMissing = true
)
public class Knife4jConfig {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Resource
    private GhKnife4jProperties ghKnife4JProperties;


    @Bean
    @ConditionalOnMissingBean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        //默认有all分组
        return  GroupedOpenApi.builder().group("all")
                .pathsToMatch(paths).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI customOpenAPI() {
        String title = Convert.toStr(ghKnife4JProperties.getTitle(), StrUtil.isEmpty(applicationName)?null:applicationName+" API文档");
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .contact(new Contact().name(Convert.toStr(ghKnife4JProperties.getAuthor(),"")))
                        .version(Convert.toStr(ghKnife4JProperties.getVersion(),"1.0"))
                        .description(Convert.toStr(ghKnife4JProperties.getDescription(),title))
                        .termsOfService(Convert.toStr(ghKnife4JProperties.getTermsOfService(),"暂无")));
    }
}
