package com.gh.common.knife4j.properties;

import com.gh.common.core.constants.ConfigPrefixConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @desc Knife4j配置属性
 * @date 2023/4/20 16:30
 * @author tianma
 */
@Data
@Configuration
@ConfigurationProperties(prefix = ConfigPrefixConstants.apiDoc)
public class MyKnife4jProperties {
    //接口文档标题
    private String title;
    //接口文档描述
    private String description;
    //服务条款
    private String termsOfService;
}
