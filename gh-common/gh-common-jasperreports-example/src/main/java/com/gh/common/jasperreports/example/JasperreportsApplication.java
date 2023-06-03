package com.gh.common.jasperreports.example;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class JasperreportsApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(JasperreportsApplication.class)
                .run(args);
        Environment env = applicationContext.getEnvironment();
        String protocol = Boolean.valueOf(env.getProperty("server.ssl.enabled")) ? "https" : "http";
        String applicationName = env.getProperty("spring.application.name");
        String knife4jEnabled = env.getProperty("knife4j.enable");
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application{} is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t"+
                        ("true".equals(knife4jEnabled)?"ApiDoc: \t{}://{}:{}/doc.html\n\n":"\t\n")+
                        "  >o< 温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行 >o<\n"+
                        "----------------------------------------------------------",
                StrUtil.isEmpty(applicationName)?"":" "+applicationName,
                protocol,port,
                protocol,InetAddress.getLocalHost().getHostAddress(),port,
                protocol,InetAddress.getLocalHost().getHostAddress(),port);
    }

}
