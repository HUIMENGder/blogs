package com.mk.xixili.pgsb.userauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.spg.permissionuser.query.UserRegisterLoginQueryBase;
import com.mk.xixili.pgsb.userauth.entity.UserInfo;
import com.mk.xixili.pgsb.userauth.query.UserRegisterQuery;
import com.mk.xixili.pgsb.userauth.vo.UserCheckVo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface UserInfoService extends IService<UserInfo> {

    void register(UserRegisterQuery queryBase)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    /**
     * 登录
     * @param queryBase 登录信息
     * @return token
     */
    String login(UserRegisterLoginQueryBase queryBase)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    /**
     * 登出
     */
    void logout();

    /**
     * 检查权限
     * @param token token
     * @return 用户ID
     */
    UserCheckVo checkPermission(String token);
}
