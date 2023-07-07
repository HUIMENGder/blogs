package com.mk.xixili.pgsb.contentmanagement.service.impl;

import com.mk.spg.commonutils.constraint.ServiceExceptionType;
import com.mk.spg.commonutils.util.StringUtil;
import com.mk.xixili.pgsb.contentmanagement.config.AppConfig;
import com.mk.xixili.pgsb.contentmanagement.entity.ExamQuestionSet;
import com.mk.xixili.pgsb.contentmanagement.service.ExamQuestionSetService;
import com.mk.xixili.pgsb.contentmanagement.service.UploadFileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {

    private final ExamQuestionSetService examQuestionSetService;
    private final AppConfig appConfig;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileId = StringUtil.generateNanoId(true, 10);
        var localFile = new File(appConfig.getUploadFilePath(), fileId);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            ServiceExceptionType.OPERATION_FAIL.withMessage("文件上传失败");
        }
        return fileId;
    }

    @Override
    public byte[] downloadFile(String fileId, HttpServletResponse response) {
        var localFile = new File(appConfig.getUploadFilePath(), fileId);
        if (!localFile.exists()) {
            // 文件不存在
            ServiceExceptionType.OPERATION_FAIL.withMessage("文件不存在");
        }
        var examQuestionSetQueryWrapper = examQuestionSetService
                .lambdaQuery().eq(ExamQuestionSet::getFileId, fileId);
        var fileName = "filename";
        if (examQuestionSetQueryWrapper.exists()) {
            fileName = examQuestionSetQueryWrapper.one().getFileName();
        }
        var uriEncodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        try (var fileInputStream = new FileInputStream(localFile)) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + uriEncodedFileName);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            ServiceExceptionType.OPERATION_FAIL.withMessage("文件下载失败");
        }
        return new byte[0];
    }
}
