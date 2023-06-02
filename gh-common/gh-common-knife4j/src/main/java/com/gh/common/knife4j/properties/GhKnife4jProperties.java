package com.gh.common.knife4j.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc Knife4j扩展配置属性
 * @date 2023/4/20 16:30
 * @author tianma
 */
@Data
@ConfigurationProperties(prefix = "knife4j.home")
@ConditionalOnProperty(
        name = {"knife4j.enable"},
        matchIfMissing = true
)
public class GhKnife4jProperties{
    //接口文档标题
    private String title;
    //接口文档描述
    private String description;
    //作者
    private String author;
    //服务条款
    private String termsOfService;
    //接口文档版本
    private String version;
}
