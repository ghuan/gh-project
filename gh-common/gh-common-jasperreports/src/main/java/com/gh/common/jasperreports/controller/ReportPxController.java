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
public class ReportPxController {

    private ImageServlet imageServlet = null;

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
