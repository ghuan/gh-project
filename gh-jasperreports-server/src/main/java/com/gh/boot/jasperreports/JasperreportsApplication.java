package com.gh.boot.jasperreports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.gh.boot.common",
        "com.gh.boot.jasperreports"
})
@Slf4j
public class JasperreportsApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(JasperreportsApplication.class)
                .run(args);
        Environment env = applicationContext.getEnvironment();
        boolean httpsEnable = Boolean.valueOf(env.getProperty("server.ssl.enabled"));
        log.info("Jasperreports服务启动成功！address:{}://{}:{}"
                ,httpsEnable ? "https" : "http"
                , InetAddress.getLocalHost().getHostAddress()
                ,env.getProperty("server.port"));
        log.warn(">o< 温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行 >o<");
    }

}
