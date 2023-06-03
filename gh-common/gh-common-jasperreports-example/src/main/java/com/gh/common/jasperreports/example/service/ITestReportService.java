package com.gh.common.jasperreports.example.service;

import com.gh.common.jasperreports.example.data.po.TestReportPO;
import org.springframework.web.servlet.ModelAndView;

/**
 *
* description：报表接口
 */
public interface ITestReportService {
	ModelAndView getTestReport(TestReportPO testReportPO);
}
