package com.gh.common.knife4j.example.data.po.v41;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/3/21 22:32
 * @since:knife4j-springdoc-openapi-demo
 */
@Data
@Schema(name = "通用分页查询参数")
public class BasePageParam {
    @Schema(description = "页码", example = "1")
    private Long pageNo = 1L;
    @Schema(description = "分页大小", example = "20")
    private Long pageSize = 99999L;
}
