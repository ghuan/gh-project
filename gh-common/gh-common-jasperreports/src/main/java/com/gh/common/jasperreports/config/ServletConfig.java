package com.gh.common.jasperreports.config;

import com.gh.common.jasperreports.controller.ReportController;
import com.gh.common.jasperreports.service.IReportService;
import com.gh.common.jasperreports.service.imp.ReportServiceImpl;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnWebApplication
public class ServletConfig {
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean printImageServlet() {
		return new ServletRegistrationBean(new ImageServlet(), "/px");
	}
}
