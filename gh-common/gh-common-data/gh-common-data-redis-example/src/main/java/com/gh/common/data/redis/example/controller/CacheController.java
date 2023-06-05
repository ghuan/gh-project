package com.gh.common.data.redis.example.controller;

import com.gh.common.data.redis.example.data.po.CacheTestPO;
import com.gh.common.data.redis.example.service.ICacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huan.gao
 * @date 2019/2/1
 */
@RestController()
@RequestMapping("cache")
@Tag(name = "缓存测试接口")
@Validated
public class CacheController {

    @Resource
    private ICacheService cacheService;

    @Operation(summary = "测试get", description = "测试get")
    @GetMapping("testGet")
    public String testGet(@Validated CacheTestPO cacheTestPO) {
        String rs = cacheService.testGet(cacheTestPO);
        return rs;
    }

    @Operation(summary = "测试Post", description = "测试Post")
    @PostMapping("testPost")
    public CacheTestPO testPost(@Validated CacheTestPO cacheTestPO) {
        CacheTestPO rs = cacheService.testPost(cacheTestPO);
        return rs;
    }
}
