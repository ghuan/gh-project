package com.gh.common.knife4j.example.data.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/3/4 19:01
 * @since:knife4j-springdoc-openapi-demo
 */
@Data
public class OSSRequest {
    @Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer code;

    @Schema(description = "目录")
    private String folder;
}
