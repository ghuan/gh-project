package com.gh.common.knife4j.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;
import java.util.Properties;

/**
 * @desc: Spring应用程序启动过程中修改spring-doc的配置
 * @author: tianma
 * @date: 2023/6/2
 */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        // 读取knife4j.enable的属性值
        String knife4jEnabled = environment.getProperty("knife4j.enable");
        knife4jEnabled = knife4jEnabled == null ? "true" : knife4jEnabled;
        // 设置knife4j和springdoc的enable属性值，已达到统一控制的目的
        properties.setProperty("knife4j.enable", knife4jEnabled);
        properties.setProperty("springdoc.api-docs.enabled", knife4jEnabled);
        if("true".equals(knife4jEnabled)){
            //设置springdoc支持model-and-view
            properties.setProperty("springdoc.model-and-view-allowed", knife4jEnabled);
            //设置拷贝knife4j的group分组配置属性到springdoc的group分组配置属性
            for (PropertySource<?> propertySource : environment.getPropertySources()) {
                if (propertySource.getSource() instanceof Map) {
                    Map<String, ?> sourceMap = (Map<String, ?>) propertySource.getSource();
                    for (Map.Entry<String, ?> entry : sourceMap.entrySet()) {
                        String propertyName = entry.getKey();
                        Object propertyValue = entry.getValue();
                        if(propertyName.indexOf("knife4j.group-configs") == 0){
                            //把knife4j.group-configs的属性值设置为springdoc.group-configs的属性值
                            properties.setProperty(propertyName.replaceFirst("knife4j\\.group-configs"
                                    ,"springdoc.group-configs"), propertyValue.toString());
                        }
                    }
                }
            }
        }

        // 创建一个新的属性源，包含设置后的属性
        PropertiesPropertySource propertySource = new PropertiesPropertySource("customSpringDockProperties", properties);

        // 将自定义的属性源插入到属性源的最前面
        propertySources.addFirst(propertySource);
    }
}
