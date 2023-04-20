package com.gh.boot.api.doc.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.gh.boot.api.doc.properties.SpringDocCustomProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
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
public class SwaggerConfig {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Resource
    private SpringDocCustomProperties springDocCustomProperties;

    @Bean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        return GroupedOpenApi.builder().group("all")
                .pathsToMatch(paths).build();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        String title = Convert.toStr(springDocCustomProperties.getTitle(), StrUtil.isEmpty(applicationName)?null:applicationName+" API文档");
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version("9.0")
                        .description(Convert.toStr(springDocCustomProperties.getDescription(),title))
                        .termsOfService(Convert.toStr(springDocCustomProperties.getTermsOfService(),"暂无")));
    }
}
