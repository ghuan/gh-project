package com.gh.boot.jasperreports.config;

import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class ServletConfig {
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean printImageServlet() {
		return new ServletRegistrationBean(new ImageServlet(), "/px");
	}
}
