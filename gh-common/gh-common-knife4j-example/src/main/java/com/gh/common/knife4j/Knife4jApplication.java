package com.gh.common.knife4j;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
//@ComponentScan(basePackages = {
////        "com.gh.common",//全部扫描
////        或自定义设置扫描路径
////        "com.gh.boot.common",
////        "com.gh.boot.api.doc"
//}
////,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SwaggerConfig.class})
//)
@Slf4j
public class Knife4jApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(Knife4jApplication.class)
                .run(args);
        Environment env = applicationContext.getEnvironment();
        String protocol = Boolean.valueOf(env.getProperty("server.ssl.enabled")) ? "https" : "http";
        String applicationName = env.getProperty("spring.application.name");
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application{} is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t"+
                        "ApiDoc: \t{}://{}:{}/doc.html\n\n" +
                        "  >o< 温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行 >o<\n"+
                        "----------------------------------------------------------",
                StrUtil.isEmpty(applicationName)?"":" "+applicationName,
                protocol,port,
                protocol,InetAddress.getLocalHost().getHostAddress(),port,
                protocol,InetAddress.getLocalHost().getHostAddress(),port);
    }

}
