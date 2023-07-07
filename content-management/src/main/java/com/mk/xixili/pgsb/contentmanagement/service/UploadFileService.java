package com.mk.xixili.pgsb.contentmanagement.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    /**
     * 上传文件
     * @param file 文件
     * @return 文件ID
     */
    String uploadFile(MultipartFile file);

    /**
     * 下载文件
     * @param fileId 文件ID
     * @param response 响应
     */
    byte[] downloadFile(String fileId, HttpServletResponse response) throws IOException;
}
