package com.gh.jasperreports.config;

import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
	@Bean
	public ServletRegistrationBean printImageServlet() {
		return new ServletRegistrationBean(new ImageServlet(), "/print_image");
	}
}
