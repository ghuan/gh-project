package com.gh.common.core.util;

import com.gh.common.core.data.AppResource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @desc: 资源工具类
 * @author: tianma
 * @date: 2023/4/28
 */
public class ResourceUtil {
    /**
     * 获取当前应用目录config或classpath下的静态资源文件
     * config资源优先
     * @param resourcePath
     * @return
     */
    public static AppResource getResource(String resourcePath) throws IOException {
        //优先读取应用根目录config下的资源文件
        String path = System.getProperty("user.dir");
        path = path + "/config/";
        File file = new File(path + "/" + resourcePath);
        AppResource appResource = new AppResource();
        if (file.exists()) {
            appResource.setInputStream(new FileInputStream(file));
            appResource.setPath(file.getPath());
            appResource.setParentPath(file.getPath().replaceFirst(file.getName(),""));
            appResource.setResource(file);
            return appResource;
        } else {
            ClassPathResource classPathResource = new ClassPathResource(resourcePath);
            if(classPathResource.exists()){
                appResource.setInputStream(classPathResource.getInputStream());
                appResource.setPath(classPathResource.getURL().toString());
                appResource.setParentPath(classPathResource.getURL().toString().replaceFirst(classPathResource.getFilename(),""));
                appResource.setResource(classPathResource);
                return appResource;
            }else {
                return null;
            }
        }
    }
}
