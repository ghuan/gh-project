package com.gh.boot.common.core.properties;

import com.gh.boot.common.core.constants.ConfigPrefixConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: 异步执行器配置项
 * @author: tianma
 * @date: 2023/5/6
 */
@Data
@Configuration
@ConfigurationProperties(prefix = ConfigPrefixConstants.commonCore+".async.executor")
public class AsyncProperties {
    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;

    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 200;

    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 10;
}
