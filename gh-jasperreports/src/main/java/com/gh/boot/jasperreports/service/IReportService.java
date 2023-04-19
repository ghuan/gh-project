package com.gh.boot.jasperreports.service;

import com.gh.boot.jasperreports.data.po.ReportPO;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *
* description：报表接口
 */
public interface IReportService {
	ModelAndView getReport(String reportName, ReportPO reportPO);
}
