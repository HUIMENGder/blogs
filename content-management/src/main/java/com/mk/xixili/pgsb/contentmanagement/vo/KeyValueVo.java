package com.mk.xixili.pgsb.contentmanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "键值对")
@Data
@AllArgsConstructor
public class KeyValueVo {

    private String key;

    private String value;
}
