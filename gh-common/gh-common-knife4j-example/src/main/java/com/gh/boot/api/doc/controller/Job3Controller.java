/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * Official Web Site: http://www.xiaominfo.com.
 */
package com.gh.boot.api.doc.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * @since:knife4j-spring-boot-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/8/6 23:42
 */
@Slf4j
@Tag(name = "文件下载190",extensions = {
        @Extension(name = "x-test-tag",properties = {
                @ExtensionProperty(name = "x-or",value = "9000")
        })
})
@ApiSupport(author = "xiaoymin@foxmail.com",order = 278)
@RestController
@RequestMapping(value = "/job3",produces = MediaType.APPLICATION_JSON_VALUE)
public class Job3Controller {

    @GetMapping("/download")
    public void download(HttpServletResponse response){
        String path="/Users/xiaoyumin/Desktop/mac-app/saas_map_poi.xls";
        log.info("download-path:{}",path);
        ServletUtil.write(response,new File(path));
    }

}
