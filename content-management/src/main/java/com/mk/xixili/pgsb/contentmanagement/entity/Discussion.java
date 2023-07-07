package com.mk.xixili.pgsb.contentmanagement.entity;

import com.mk.spg.commonutils.bo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(name = "帖子")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Discussion extends BaseEntity {

    @Schema(name = "帖子ID")
    private String discussionId;

    @Schema(name = "标题")
    private String title;

    @Schema(name = "内容")
    private String content;

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

    @Schema(name = "回复帖子ID")
    private String replyTo;
}
