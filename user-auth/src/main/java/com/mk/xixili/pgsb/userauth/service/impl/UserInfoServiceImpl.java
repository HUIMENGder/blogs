package com.mk.xixili.pgsb.userauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.spg.permissionuser.bo.User;
import com.mk.spg.permissionuser.query.UserRegisterLoginQueryBase;
import com.mk.spg.permissionuser.service.UserService;
import com.mk.spg.permissionuser.util.TokenUtil;
import com.mk.spg.permissionuser.util.UserType;
import com.mk.xixili.pgsb.userauth.entity.UserInfo;
import com.mk.xixili.pgsb.userauth.mapper.UserInfoMapper;
import com.mk.xixili.pgsb.userauth.query.UserRegisterQuery;
import com.mk.xixili.pgsb.userauth.service.UserInfoService;
import com.mk.xixili.pgsb.userauth.vo.UserCheckVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    private final UserService userService;
    private final TokenUtil tokenUtil;

    @Override
    @Transactional
    public void register(UserRegisterQuery queryBase)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var userObj = userService.register(new UserRegisterLoginQueryBase(queryBase.getUsername(), queryBase.getPassword()));
        var userInfo = new UserInfo(
                userObj.getUserId(), null, queryBase.getGender(), queryBase.getBirthdayYear(),
                queryBase.getTargetCollege(), queryBase.getTargetMajor()
        );
        this.save(userInfo);
    }

    @Override
    public String login(UserRegisterLoginQueryBase queryBase)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var loginVo = userService.login(queryBase);
        return loginVo.token();
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public UserCheckVo checkPermission(String token) {
        var vo = new UserCheckVo();
        var userId = tokenUtil.checkPermissionViaTokenValue(UserType.USER, token).getUserId();
        var username = userService.lambdaQuery().eq(User::getUserId, userId).one().getUsername();
        vo.setUserId(userId);
        vo.setUsername(username);
        return vo;
    }
}
