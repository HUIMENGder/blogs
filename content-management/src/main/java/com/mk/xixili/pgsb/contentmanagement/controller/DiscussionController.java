package com.mk.xixili.pgsb.contentmanagement.controller;

import com.mk.spg.commonutils.annotation.ApiLog;
import com.mk.spg.commonutils.bo.ApiResult;
import com.mk.spg.commonutils.constraint.ServiceExceptionType;
import com.mk.spg.commonutils.exception.ServiceException;
import com.mk.spg.commonutils.util.ResultUtil;
import com.mk.spg.commonutils.util.StringUtil;
import com.mk.spg.entityapis.bo.GeneralQuery;
import com.mk.spg.entityapis.controller.AllInOneController;
import com.mk.spg.entityapis.util.AllInOneActionApiUtil;
import com.mk.xixili.pgsb.contentmanagement.entity.Discussion;
import com.mk.xixili.pgsb.contentmanagement.service.DiscussionService;
import com.mk.xixili.pgsb.contentmanagement.util.PermissionCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "帖子")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-management/discussion")
public class DiscussionController implements AllInOneController<Discussion> {

    private final DiscussionService discussionService;
    private final PermissionCheckUtil permissionCheckUtil;

    @Override
    @ApiLog
    @Operation(summary = "AllInOneProcess")
    @PostMapping("/process")
    public ApiResult<?> allInOneProcess(@RequestBody @Valid GeneralQuery generalQuery) throws ServiceException, NoSuchMethodException {
        return AllInOneActionApiUtil.process(generalQuery, discussionService);
    }

    @Override
    @ApiLog
    @Operation(summary = "添加帖子")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody @Valid Discussion discussion) {
        var userVo = permissionCheckUtil.checkUserAuth();
        discussion.setDiscussionId(StringUtil.generateNanoId(true, 10));
        discussion.setAuthorId(userVo.getUserId());
        discussion.setAuthorName(userVo.getUsername());
        discussion.setVisitCount(0);
        discussion.setGoodCount(0);
        AllInOneActionApiUtil.add(discussion, null, discussionService);
        return ResultUtil.success(discussion.getDiscussionId());
    }

    @ApiLog
    @Operation(summary = "添加帖子状态（点赞或访问量）")
    @GetMapping("/add-status")
    public ApiResult<Void> addStatus(@RequestParam String type, @RequestParam String discussionId) {
        var queryWrapper = discussionService.lambdaQuery().eq(Discussion::getDiscussionId, discussionId);
        if (!queryWrapper.exists()) {
            ServiceExceptionType.OPERATION_FAIL.withMessage("帖子不存在");
        }
        var one = queryWrapper.one();
        if ("good".equals(type)) {
            one.setGoodCount(one.getGoodCount() + 1);
        } else if ("visit".equals(type)) {
            one.setVisitCount(one.getVisitCount() + 1);
        } else {
            ServiceExceptionType.OPERATION_FAIL.withMessage("类型错误");
        }
        discussionService.updateById(one);
        return ResultUtil.success();
    }
}
