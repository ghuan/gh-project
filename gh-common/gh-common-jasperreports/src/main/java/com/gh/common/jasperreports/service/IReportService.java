package com.gh.common.jasperreports.service;

import com.gh.common.jasperreports.data.dto.ReportBuilderDTO;
import org.springframework.web.servlet.ModelAndView;

/**
 *
* description：报表接口
 */
public interface IReportService {
	ModelAndView buildReport(ReportBuilderDTO reportBuilderDTO);
}
