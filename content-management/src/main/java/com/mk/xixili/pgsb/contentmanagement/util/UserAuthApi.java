package com.mk.xixili.pgsb.contentmanagement.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("user-auth")
public interface UserAuthApi {

    @GetMapping("/api/user-auth/check-permission")
    Map<String, Object> checkUserAuth(@RequestParam String token);
}
