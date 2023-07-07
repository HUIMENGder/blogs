package com.mk.xixili.pgsb.contentmanagement.config;

import com.mk.spg.commonutils.util.StringUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Value("${service.uploadFilePath:}")
    private String uploadFilePath;

    private final ApplicationContext applicationContext;

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    @PostConstruct
    public void init() {
        if (StringUtil.isEmpty(uploadFilePath)) {
            log.error("uploadFilePath is empty, exit.");
            SpringApplication.exit(applicationContext);
        }
        // 初始化目录
        var dirFile = new File(uploadFilePath);
        if ((!dirFile.exists() || !dirFile.isDirectory()) && (!dirFile.mkdirs())) {
            log.error("mkdirs failed, exit.");
            SpringApplication.exit(applicationContext);
        }
    }
}
