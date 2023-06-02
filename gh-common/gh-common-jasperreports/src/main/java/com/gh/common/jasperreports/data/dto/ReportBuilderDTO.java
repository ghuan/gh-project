package com.gh.common.jasperreports.data.dto;

import com.gh.common.jasperreports.enums.DocType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @desc: 报表生成入参
 * @author: tianma
 * @date: 2023/4/19
 */
@Data
@Builder
public class ReportBuilderDTO {

    @Schema(description = "生成的报表文档类型",defaultValue = "html")
    @Builder.Default
    private DocType docType = DocType.HTML;

    @Schema(description = "是否内联",defaultValue = "true")
    @Builder.Default
    private Boolean inline = true;

    @Schema(description = "targetFileName生成的报表文件名",defaultValue = "随机码")
    private String targetFileName;

    @Schema(description = "报表jrxml模板名",defaultValue = "test")
    @NotEmpty(message = "templateFileName-报表jrxml模板名不能为空")
    private String templateFileName;

    @Schema(description = "父报表加载子报表传递的子报表所在绝对路径",defaultValue = "SUBREPORT_DIR")
    @Builder.Default
    private String subReportDir = "SUBREPORT_DIR";

    @Schema(description = "报表数据")
    private ReportDataDTO data;

}
