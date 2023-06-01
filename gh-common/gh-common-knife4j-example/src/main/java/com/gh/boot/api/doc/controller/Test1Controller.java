package com.gh.boot.api.doc.controller;

import cn.hutool.core.util.RandomUtil;
import com.gh.boot.api.doc.data.po.FileRequestVo;
import com.gh.boot.api.doc.data.po.FileResp;
import com.gh.boot.api.doc.data.po.MyBodyTest;
import com.gh.boot.api.doc.data.po.v41.ConfigPageParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/3/14 15:21
 * @since:knife4j-springdoc-openapi-demo
 */
@Tag(name = "测试模块")
@RestController
@RequestMapping("/test")
public class Test1Controller {

    @Operation(summary = "描述1")
    @PostMapping("/description")
    public ResponseEntity<ConfigPageParam> description(@ParameterObject ConfigPageParam configPageParam){
        return ResponseEntity.ok(configPageParam);
    }

    @Operation(summary = "测试一下3")
    @PostMapping(value = "/module/upload3",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule3(MyBodyTest fileResp, HttpServletResponse response){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }


    @Operation(summary = "测试一下2")
    @PostMapping(value = "/module/upload2",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule2(@RequestBody MyBodyTest fileResp, HttpServletResponse response){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }

    @Operation(summary = "测试一下1")
    @PostMapping(value = "/module/upload1",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule1(@RequestBody FileResp fileResp, HttpServletResponse response){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }

    @Operation(summary = "测试一下")
    @PostMapping(value = "/module/upload",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule(FileRequestVo fileRequestVo, FileResp fileResp, HttpServletResponse response){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }

}
