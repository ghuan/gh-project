package com.gh.boot.jasperreports.data.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @desc: 报表入参
 * @author: tianma
 * @date: 2023/4/19
 */
@Data
public class ReportPO {

    @NotBlank(message = "文档类型不能为空")
    private String docType;
}
