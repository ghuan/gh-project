package com.gh.common.data.redis.example.data.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/6/5
 */
@Data
public class CacheTestPO {
    @Schema(description = "用户名",nullable = false)
    @NotEmpty(message = "用户名不能为空")
    private String username;
}
