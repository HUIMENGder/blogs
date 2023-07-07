package com.mk.xixili.pgsb.contentmanagement.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("admin-session")
public interface AdminAuthApi {

    @GetMapping("/api/management/system/check-permission")
    Map<String, Object> checkAdminAuth(@RequestParam String token);
}
