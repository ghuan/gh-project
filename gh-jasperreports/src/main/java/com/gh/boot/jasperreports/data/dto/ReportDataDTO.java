package com.gh.boot.jasperreports.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @desc: 报表数据入参
 * @author: tianma
 * @date: 2023/4/19
 */
@Data
public class ReportDataDTO {
    @Schema(description = "报表列数据")
    private List list;
}
