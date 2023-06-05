package com.gh.common.jasperreports.example.data.po;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @desc: 测试报表入参
 * @author: tianma
 * @date: 2023/4/28
 */
@Data
public class TestReportPO{
    @NotEmpty(message = "用户名不能为空")
    private String username;
}
