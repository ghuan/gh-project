package com.gh.boot.api.doc.properties;

import com.gh.boot.common.core.constants.ProjectConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @desc api文档配置属性
 * @date 2023/4/20 16:30
 * @author tianma
 */
@Data
@Configuration
@ConfigurationProperties(prefix = ProjectConstants.apiDoc)
public class SpringDocCustomProperties {
    //接口文档标题
    private String title;
    //接口文档描述
    private String description;
    //服务条款
    private String termsOfService;
}
