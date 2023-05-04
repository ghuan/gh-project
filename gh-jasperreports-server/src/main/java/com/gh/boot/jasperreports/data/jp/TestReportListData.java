package com.gh.boot.jasperreports.data.jp;

import com.gh.boot.jasperreports.data.dto.ReportDataDTO;
import lombok.Data;

import java.util.List;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/4/28
 */
@Data
public class TestReportListData extends ReportDataDTO {
    private String KSMC;
    private String ZYHM;
    private String BRXM;
    private String FPHM;
    private String FYHJ;
    private String ZFHJ;
    private String JKHJ;
}
