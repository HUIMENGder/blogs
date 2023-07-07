package com.mk.xixili.pgsb.contentmanagement.util;

import com.mk.spg.commonutils.constraint.ServiceExceptionType;
import com.mk.spg.commonutils.exception.ServiceException;
import com.mk.xixili.pgsb.contentmanagement.vo.UserCheckVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * 检查管理员或用户的权限，是否登录
 */
@Component
@RequiredArgsConstructor
public class PermissionCheckUtil {

    private final AdminAuthApi adminAuthApi;
    private final UserAuthApi userAuthApi;

    public void checkAdminAuth() {
        var attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        var token = attr.getRequest().getHeader("X-Token");
        var permitted = false;
        if (token != null) {
            var result = adminAuthApi.checkAdminAuth(token);
            permitted = result.containsKey("code") && (Integer) result.get("code") == 200;
        }
        if (!permitted) {
            ServiceExceptionType.FORBIDDEN.newException();
        }
    }

    public UserCheckVo checkUserAuth() {
        var attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        var token = attr.getRequest().getHeader("X-Token");
        var permitted = false;
        String userId = null;
        String username = null;
        if (token != null) {
            var result = userAuthApi.checkUserAuth(token);
            permitted = result.containsKey("code") && (Integer) result.get("code") == 200;
            if (!result.containsKey("data")) {
                ServiceExceptionType.FORBIDDEN.newException();
            }
            var data = (Map<String, String>) result.get("data");
            userId = data.get("userId");
            username = data.get("username");
        }
        if (!permitted) {
            ServiceExceptionType.FORBIDDEN.newException();
        }
        var vo = new UserCheckVo();
        vo.setUserId(userId);
        vo.setUsername(username);
        return vo;
    }

    public void checkEitherAuth()  {
        try {
            checkAdminAuth();
        } catch (ServiceException e) {
            checkUserAuth();
        }
    }
}
