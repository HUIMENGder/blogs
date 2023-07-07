package com.mk.xixili.pgsb.userauth.query;

import com.mk.xixili.pgsb.userauth.entity.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户注册查询")
@Data
public class UserRegisterQuery {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private GenderType gender;

    @Schema(description = "出生年份")
    private Integer birthdayYear;

    @Schema(description = "目标院校")
    private String targetCollege;

    @Schema(description = "目标专业")
    private String targetMajor;
}
