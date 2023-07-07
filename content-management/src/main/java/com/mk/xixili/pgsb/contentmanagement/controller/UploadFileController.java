package com.mk.xixili.pgsb.contentmanagement.controller;

import com.mk.spg.commonutils.annotation.ApiLog;
import com.mk.spg.commonutils.bo.ApiResult;
import com.mk.spg.commonutils.exception.ServiceException;
import com.mk.spg.commonutils.util.ResultUtil;
import com.mk.xixili.pgsb.contentmanagement.service.UploadFileService;
import com.mk.xixili.pgsb.contentmanagement.util.PermissionCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "文件管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-management/upload-file")
public class UploadFileController {

    private final PermissionCheckUtil permissionCheckUtil;
    private final UploadFileService uploadFileService;

    @ApiLog
    @Operation(summary = "上传文件")
    @PostMapping("/upload-file")
    public ApiResult<String> uploadFile(@RequestPart MultipartFile file) throws InterruptedException {
        permissionCheckUtil.checkEitherAuth();
        Thread.sleep(3000);
        return ResultUtil.success(uploadFileService.uploadFile(file));
    }

    @ApiLog
    @Operation(summary = "上传文件（WangEditor）")
    @PostMapping("/upload-file-wang-editor")
    public Map<String, Object> uploadFileWangEditor(@RequestPart MultipartFile file) {
        permissionCheckUtil.checkEitherAuth();
        try {
            var fileId = uploadFileService.uploadFile(file);
            var result = new HashMap<String, Object>();
            result.put("errno", 0);
            var data = new HashMap<String, String>();
            data.put("url", "/api/content-management/upload-file/download-file?fileId=" + fileId);
            result.put("data", data);
            return result;
        } catch (ServiceException e) {
            var result = new HashMap<String, Object>();
            result.put("errno", 1);
            result.put("message", e.getMessage());
            return result;
        }
    }

    @ApiLog
    @Operation(summary = "下载文件")
    @GetMapping("/download-file")
    public byte[] downloadFile(@RequestParam String fileId, HttpServletResponse response) throws IOException {
        return uploadFileService.downloadFile(fileId, response);
    }
}
