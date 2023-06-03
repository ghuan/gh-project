/*
 * Copy right © 2022 浙江力石科技股份有限公司 All Rights Reserved.
 * Official Web Site: http://lishiots.com
 */
package com.gh.common.knife4j.example.controller;

import cn.hutool.core.util.RandomUtil;
import com.gh.common.knife4j.example.data.po.FileResp;
import com.gh.common.knife4j.example.data.po.OSSRequest;
import com.gh.common.knife4j.example.data.po.FileRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @since:knife4j-springdoc-openapi-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/8/6 16:24
 */
@Slf4j
@RestController
@RequestMapping("file")
@Tag(name = "文件上传")
public class FileController {

    @Operation(summary = "附件上传",description = "附近上传-xxx",requestBody = @RequestBody(content = {
            @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,schema = @Schema(type = "object"),
            schemaProperties = {
                    @SchemaProperty(name = "file",array = @ArraySchema(schema = @Schema(type = "string",format = "binary",requiredMode = Schema.RequiredMode.REQUIRED))),
                    @SchemaProperty(name = "module",schema = @Schema(type = "string",required = true)),
                    @SchemaProperty(name = "bizNo",schema = @Schema(type = "string",required = true)),
                    @SchemaProperty(name = "bizType",schema = @Schema(type = "integer",required = true))
            })
    }))
    @PostMapping(value = "/module/upload1",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule1(FileRequestVo fileRequestVo){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }


    @PostMapping(value = "/module/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule(FileRequestVo fileRequestVo){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }

    @PostMapping(value = "/oss",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> oss(@RequestPart MultipartFile file, OSSRequest ossRequest){
        log.info("req:{}",ossRequest.toString());
        log.info("file-name:{}",file.getOriginalFilename());
        return ResponseEntity.ok(RandomUtil.randomString(12));
    }

    @Operation(description = "单纯文件上传，无任何参数",summary = "单纯文件上传")
    @PostMapping("/upload")
    public ResponseEntity<FileResp> upload(@RequestParam("file") MultipartFile file){
        FileResp fileResp=new FileResp();
        fileResp.setSize(file.getSize());
        fileResp.setName(file.getOriginalFilename());
        fileResp.setSuccess(true);
        fileResp.setRandom(RandomUtil.randomString(12));

        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "文件上传-带参数")
    @Parameters({
            @Parameter(name = "file",description = "文件",required = true,in=ParameterIn.DEFAULT,ref = "file"),
            @Parameter(name = "name",description = "文件名称",required = true),
    })
    @PostMapping("/uploadParam")
    public ResponseEntity<FileResp> uploadParam(@RequestParam("file")MultipartFile file,@RequestParam("name") String name){
        FileResp fileResp=new FileResp();
        fileResp.setSize(file.getSize());
        fileResp.setName(file.getOriginalFilename());
        fileResp.setSuccess(true);
        fileResp.setRandom(RandomUtil.randomString(12)+",receiveName:"+name);
        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "文件上传-带参数Header")
    @Parameters({
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "file",description = "文件",required = true,in=ParameterIn.DEFAULT,ref = "file"),
            @Parameter(name = "name",description = "文件名称",required = true),
    })
    @PostMapping("/uploadParamHeader")
    public ResponseEntity<FileResp> uploadParamHeader(@RequestHeader("token") String token, @RequestParam("file")MultipartFile file, @RequestParam("name") String name){
        FileResp fileResp=new FileResp();
        fileResp.setSize(file.getSize());
        fileResp.setName(file.getOriginalFilename());
        fileResp.setSuccess(true);
        fileResp.setRandom(RandomUtil.randomString(12)+",receiveName:"+name+",token:"+token);
        return ResponseEntity.ok(fileResp);
    }


    @Operation(summary = "文件上传-带参数Path")
    @Parameters({
            @Parameter(name = "id",description = "文件id",in = ParameterIn.PATH),
            @Parameter(name = "file",description = "文件",required = true,in=ParameterIn.DEFAULT,ref = "file"),
            @Parameter(name = "name",description = "文件名称",required = true),
    })
    @PostMapping("/uploadParam/{id}")
    public ResponseEntity<FileResp> uploadParamPath(@PathVariable("id")String id,@RequestParam("file")MultipartFile file,@RequestParam("name") String name){
        FileResp fileResp=new FileResp();
        fileResp.setSize(file.getSize());
        fileResp.setName(file.getOriginalFilename());
        fileResp.setSuccess(true);
        fileResp.setRandom(RandomUtil.randomString(12)+",receiveName:"+name+",id:"+id);
        return ResponseEntity.ok(fileResp);
    }


    @Operation(summary = "多文件上传")
    @Parameter(name = "file",description = "文件",in = ParameterIn.DEFAULT,ref="file")
    @PostMapping("/uploadBatch")
    public ResponseEntity<List<FileResp>> uploadBatch(@RequestParam("files") List<MultipartFile> files){
        List<FileResp> fileResps=new ArrayList<>();
        for (MultipartFile file:files){
            FileResp fileResp=new FileResp();
            fileResp.setSize(file.getSize());
            fileResp.setName(file.getOriginalFilename());
            fileResp.setSuccess(true);
            fileResp.setRandom(RandomUtil.randomString(12));

            fileResps.add(fileResp);
        }

        return ResponseEntity.ok(fileResps);
    }


}
