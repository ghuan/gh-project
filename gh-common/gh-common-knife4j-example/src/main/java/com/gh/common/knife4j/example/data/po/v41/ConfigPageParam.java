package com.gh.common.knife4j.example.data.po.v41;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/3/21 22:32
 * @since:knife4j-springdoc-openapi-demo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "修改分页查询参数")
public class ConfigPageParam extends BasePageParam{

    @Schema(description = "描述")
    private String sortType;
}
