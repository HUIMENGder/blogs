package com.mk.xixili.pgsb.contentmanagement.controller;

import com.mk.spg.commonutils.annotation.ApiLog;
import com.mk.spg.commonutils.bo.ApiResult;
import com.mk.spg.commonutils.util.ResultUtil;
import com.mk.spg.commonutils.util.StringUtil;
import com.mk.spg.entityapis.bo.GeneralQuery;
import com.mk.spg.entityapis.controller.AllInOneController;
import com.mk.spg.entityapis.util.AllInOneActionApiUtil;
import com.mk.xixili.pgsb.contentmanagement.entity.ExamQuestionSet;
import com.mk.xixili.pgsb.contentmanagement.entity.SubjectCategory;
import com.mk.xixili.pgsb.contentmanagement.service.ExamQuestionSetService;
import com.mk.xixili.pgsb.contentmanagement.util.PermissionCheckUtil;
import com.mk.xixili.pgsb.contentmanagement.vo.KeyValueVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "真题管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-management/exam-question-set")
public class ExamQuestionSetController implements AllInOneController<ExamQuestionSet> {

    private final PermissionCheckUtil permissionCheckUtil;
    private final ExamQuestionSetService examQuestionSetService;

    @ApiLog
    @Operation(summary = "试题集AIO接口")
    @PostMapping("/all-in-one")
    @Override
    public ApiResult<?> allInOneProcess(@RequestBody @Valid GeneralQuery generalQuery) {
        permissionCheckUtil.checkEitherAuth();
        return AllInOneActionApiUtil.process(generalQuery, examQuestionSetService);
    }

    @ApiLog
    @Operation(summary = "添加试题集")
    @PostMapping("/add")
    @Override
    public ApiResult<String> add(@RequestBody @Valid ExamQuestionSet examQuestionSet) {
        permissionCheckUtil.checkAdminAuth();
        examQuestionSet.setQuestionSetId(StringUtil.generateNanoId(true, 10));
        AllInOneActionApiUtil.add(examQuestionSet, null, examQuestionSetService);
        return ResultUtil.success(examQuestionSet.getQuestionSetId());
    }

    @ApiLog
    @Operation(summary = "获取学科分类")
    @GetMapping("/get-subject-categories")
    public ApiResult<List<KeyValueVo>> getSubjectCategories() {
        permissionCheckUtil.checkAdminAuth();
        var list = SubjectCategory.getAll().stream()
                .map(c -> new KeyValueVo(c.name(), c.getDisplayName()))
                .toList();
        return ResultUtil.success(list);
    }
}
