package com.gh.boot.jasperreports.service;

import com.gh.boot.jasperreports.data.dto.ReportBuilderDTO;
import org.springframework.web.servlet.ModelAndView;

/**
 *
* description：报表接口
 */
public interface IReportService {
	ModelAndView buildReport(ReportBuilderDTO reportBuilderDTO);
}
