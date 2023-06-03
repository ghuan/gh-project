package com.gh.common.knife4j.example.data.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/3/5 18:58
 * @since:knife4j-springdoc-openapi-demo
 */
@Data
public class FileRequestVo {

    MultipartFile[] file;

    @Schema(description = "模块")
    private String module;

    private String bizNo;

    private Integer bizType=0;
}
