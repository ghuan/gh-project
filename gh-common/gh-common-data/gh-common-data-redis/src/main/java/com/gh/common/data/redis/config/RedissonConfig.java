package com.gh.common.data.redis.config;

import com.gh.common.data.redis.redisson.impl.RedisDistributedLocker;
import com.gh.common.data.redis.util.RedisLockUtil;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * com.ichoice.hsp.cache.redisson 配置
 */

@Data
@Configuration
@Import({RedisDistributedLocker.class, RedisLockUtil.class})
public class RedissonConfig {

    @PostConstruct
    public void init() {
        System.out.println("RedissonConfig init");
    }

//    @Bean
//    @ConditionalOnProperty(
//            name = {"spring"},
//            havingValue = "redis"
//    )
//    @Primary
//    public RedissonClient redissonClient() {
////        Properties properties = new Properties();
////        properties.setProperty("serverAddr", serverAddr);
////        properties.setProperty("namespace", namespace);
////        ConfigService configService= NacosFactory.createConfigService(properties);
////        String serviceConfig = configService.getConfig(dataId, groupId, 3000);
////        Config config = Config.fromYAML(serviceConfig);
////        return Redisson.create(config);
//        return null;
//    }
//
//    @Bean
//    public RedissonClient redissonNullClient() {
////        Properties properties = new Properties();
////        properties.setProperty("serverAddr", serverAddr);
////        properties.setProperty("namespace", namespace);
////        ConfigService configService= NacosFactory.createConfigService(properties);
////        String serviceConfig = configService.getConfig(dataId, groupId, 3000);
////        Config config = Config.fromYAML(serviceConfig);
////        return Redisson.create(config);
//        return Redisson.create();
//    }

}