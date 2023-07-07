package com.mk.xixili.pgsb.contentmanagement.entity;

import com.mk.spg.commonutils.bo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "试题集")
@Data
@EqualsAndHashCode(callSuper = true)
public class ExamQuestionSet extends BaseEntity {

    @Schema(description = "试题集ID")
    private String questionSetId;

    @Schema(description = "试题集名称")
    private String name;

    @Schema(description = "试题集文件ID")
    private String fileId;

    @Schema(description = "试题集文件名")
    private String fileName;

    @Schema(description = "试题集年份")
    private Integer questionYear;

    @Schema(description = "所属院校")
    private String collegeName;

    @Schema(description = "所属专业")
    private String majorName;

    @Schema(description = "科目名称")
    private String subjectName;

    @Schema(description = "本年份本学科类目国家线")
    private Integer nationalScoreLine;

    @Schema(description = "本年份本专业院校线")
    private Integer collegeScoreLine;

    @Schema(description = "本专业所属学科类目")
    private SubjectCategory subjectCategory;

    @Schema(description = "参考链接")
    private String referenceUrl;
}
