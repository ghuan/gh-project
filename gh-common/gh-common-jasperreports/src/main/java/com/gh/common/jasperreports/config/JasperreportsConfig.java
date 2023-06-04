package com.gh.common.jasperreports.config;

import com.gh.common.jasperreports.controller.ReportPxController;
import com.gh.common.jasperreports.view.ReportViewGenerator;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnWebApplication
@Import(ReportViewGenerator.class)
public class JasperreportsConfig {
	@Bean
	public ServletRegistrationBean printImageServlet() {
		return new ServletRegistrationBean(new ImageServlet(), "/px");
	}

	@Bean
	@ConditionalOnMissingBean
	public ReportPxController reportPxController() {
		return new ReportPxController();
	}
}
