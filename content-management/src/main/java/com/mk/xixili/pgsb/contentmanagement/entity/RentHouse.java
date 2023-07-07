package com.mk.xixili.pgsb.contentmanagement.entity;

import com.mk.spg.commonutils.bo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(name = "租房房屋信息")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RentHouse extends BaseEntity {

    @Schema(name = "房屋ID")
    private String houseId;

    @Schema(name = "标题")
    private String title;

    @Schema(name = "内容")
    private String content;

    @Schema(name = "位置")
    private String location;

    @Schema(name = "价格")
    private Integer price;

    @Schema(name = "照片ID")
    private String photosId;

    @Schema(name = "作者ID")
    private String authorId;

    @Schema(name = "作者名")
    private String authorName;

    @Schema(name = "访问量")
    private Integer visitCount;

    @Schema(name = "点赞数")
    private Integer goodCount;

    @Schema(name = "标签，以逗号分割")
    private String tags;
}
