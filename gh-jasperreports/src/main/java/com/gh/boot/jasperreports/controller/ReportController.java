package com.gh.boot.jasperreports.controller;

import com.gh.boot.jasperreports.constant.ReportConstant;
import com.gh.boot.jasperreports.data.po.ReportPO;
import com.gh.boot.jasperreports.service.IReportService;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author huan.gao
 * @date 2019/2/1
 */
@RestController
public class ReportController {

	private ImageServlet imageServlet = null;
	@Autowired
	private IReportService reportService;

	@GetMapping("report/{reportName}")
	public ModelAndView getReport(@PathVariable("reportName") final String reportName, @Valid ReportPO reportPO) {
		return reportService.getReport(reportName,reportPO);
	}
	@GetMapping(value = "print_image")
	public void doPrintImage(HttpServletRequest request,
							 HttpServletResponse response) {
		try {
			if (imageServlet == null) {
				imageServlet = new ImageServlet();
			}
			imageServlet.service(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
