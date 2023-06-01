package com.gh.boot.jasperreports.controller;

import com.gh.boot.jasperreports.data.po.TestReportPO;
import com.gh.boot.jasperreports.service.ITestReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author huan.gao
 * @date 2019/2/1
 */
@RestController()
@RequestMapping("report")
@Tag(name = "测试报表接口")
@Validated
public class ReportTestController {

    @Autowired
    private ITestReportService testReportService;

    @Operation(summary = "测试报表", description = "测试报表")
    @GetMapping("test")
    public ModelAndView getTestReport(@Validated @ParameterObject TestReportPO testReportPO) {
        return testReportService.getTestReport(testReportPO);
    }
}
