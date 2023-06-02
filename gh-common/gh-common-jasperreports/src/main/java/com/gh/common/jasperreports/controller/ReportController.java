package com.gh.common.jasperreports.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huan.gao
 * @date 2019/2/1
 */
@RestController
public class ReportController {

    private ImageServlet imageServlet = null;
//    @Autowired
//    private IReportService reportService;

//    @Operation(summary = "请求报表", description = "请求报表")
//    @GetMapping("report/{reportName}")
//    public ModelAndView getReport(
//            @PathVariable("reportName")
//            String reportName,
//            @Validated
//            @ParameterObject ReportPO reportPO) {
//        return reportService.getReport(reportName, reportPO);
//    }

    @GetMapping(value = "px")
    @Operation(hidden = true,summary = "报表px图片获取")
    public void px(HttpServletRequest request,
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
