package com.mk.xixili.pgsb.userauth.controller;

import com.mk.spg.commonutils.annotation.ApiLog;
import com.mk.spg.commonutils.bo.ApiResult;
import com.mk.spg.commonutils.util.ResultUtil;
import com.mk.spg.permissionuser.annotion.NeedUserPermission;
import com.mk.spg.permissionuser.bo.User;
import com.mk.spg.permissionuser.query.UserRegisterLoginQueryBase;
import com.mk.spg.permissionuser.service.UserService;
import com.mk.spg.permissionuser.util.TokenUtil;
import com.mk.xixili.pgsb.userauth.entity.UserInfo;
import com.mk.xixili.pgsb.userauth.query.UserRegisterQuery;
import com.mk.xixili.pgsb.userauth.service.UserInfoService;
import com.mk.xixili.pgsb.userauth.util.HttpUtil;
import com.mk.xixili.pgsb.userauth.vo.UserCheckVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Tag(name = "用户认证")
@RestController
@RequestMapping("/api/user-auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserInfoService userInfoService;
    private final UserService userService;
    private final HttpUtil httpUtil;

    @ApiLog
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public ApiResult<Void> register(@RequestBody UserRegisterQuery query)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        userInfoService.register(query);
        return ResultUtil.success();
    }

    @ApiLog
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody UserRegisterLoginQueryBase queryBase)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var token = userInfoService.login(queryBase);
        return ResultUtil.success(token);
    }

    @ApiLog
    @Operation(summary = "用户登出")
    @GetMapping("/logout")
    public ApiResult<Void> logout() {
        userInfoService.logout();
        return ResultUtil.success();
    }

    @ApiLog
    @Operation(summary = "检查权限")
    @GetMapping("/check-permission")
    public ApiResult<UserCheckVo> checkPermission(@RequestParam String token) {
        return ResultUtil.success(userInfoService.checkPermission(token));
    }

    @ApiLog
    @Operation(summary = "获取用户信息")
    @GetMapping("/get-user-info")
    @NeedUserPermission
    public ApiResult<UserInfo> getUserInfo(@RequestParam String userId, @RequestParam Boolean isMyself) {
        if (Boolean.TRUE.equals(isMyself)) {
            userId = httpUtil.getUserId();
        }
        var userInfo = userInfoService.lambdaQuery().eq(UserInfo::getUserId, userId).one();
        var user = userService.lambdaQuery().eq(User::getUserId, userId).one();
        userInfo.setUsername(user.getUsername());
        return ResultUtil.success(userInfo);
    }
}
