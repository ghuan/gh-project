package com.gh.boot.jasperreports.controller;

import com.gh.boot.jasperreports.constant.ReportConstant;
import com.gh.boot.jasperreports.data.po.ReportPO;
import com.gh.boot.jasperreports.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
@Tag(name = "报表通用接口")
public class ReportController {

	private ImageServlet imageServlet = null;
	@Autowired
	private IReportService reportService;

	@Operation(summary = "请求报表1",description = "请求报表")
	@GetMapping("report/{reportName}")
	public ModelAndView getReport(@PathVariable("reportName") String reportName, @Valid ReportPO reportPO) {
		return reportService.getReport(reportName,reportPO);
	}

	@PostMapping("test")
	@Operation(summary = "test1",description = "test")
	public String test(@RequestBody @Valid ReportPO reportPO){
		return "test";
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
