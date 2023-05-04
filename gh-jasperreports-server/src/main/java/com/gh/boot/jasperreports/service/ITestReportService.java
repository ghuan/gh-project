package com.gh.boot.jasperreports.service;

import com.gh.boot.jasperreports.data.po.TestReportPO;
import org.springframework.web.servlet.ModelAndView;

/**
 *
* description：报表接口
 */
public interface ITestReportService {
	ModelAndView getTestReport(TestReportPO testReportPO);
}
