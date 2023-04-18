package com.gh.jasperreports.service;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *
* description：报表接口
 */
public interface IReportService {
	ModelAndView getReport(String reportName,Map<String,Object> parameters);
}
