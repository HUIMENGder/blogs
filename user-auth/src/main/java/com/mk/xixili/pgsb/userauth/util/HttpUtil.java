package com.mk.xixili.pgsb.userauth.util;

import com.mk.spg.permissionuser.bo.Token;
import com.mk.spg.permissionuser.service.TokenService;
import com.mk.spg.permissionuser.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpUtil {

    private final TokenService tokenService;

    /**
     * 获取当前请求的用户ID
     * @return 用户ID
     */
    public String getUserId() {
        var token = TokenUtil.getCurrentRequest().getHeader("X-Token");
        if (token == null) {
            return null;
        }
        var queryWrapper = tokenService.lambdaQuery().eq(Token::getTokenValue, token);
        if (!queryWrapper.exists()) {
            return null;
        }
        return queryWrapper.one().getUserId();
    }
}
