package com.gh.jasperreports.controller;

import com.gh.jasperreports.data.constant.ReportConstant;
import com.gh.jasperreports.service.IReportService;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public ModelAndView getReport(@PathVariable(ReportConstant.REPORT_NAME) final String reportName, @RequestParam Map<String,Object> parameters) {
		return reportService.getReport(reportName,parameters);
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
