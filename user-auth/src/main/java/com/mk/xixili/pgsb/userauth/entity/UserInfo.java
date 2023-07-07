package com.mk.xixili.pgsb.userauth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mk.spg.commonutils.bo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(description = "用户信息")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "用户名")
    @TableField(exist = false)
    private String username;

    @Schema(description = "性别")
    private GenderType gender;

    @Schema(description = "出生年份")
    private Integer birthdayYear;

    @Schema(description = "目标院校")
    private String targetCollege;

    @Schema(description = "目标专业")
    private String targetMajor;
}
