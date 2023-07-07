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
import com.mk.xixili.pgsb.contentmanagement.entity.RentHouse;
import com.mk.xixili.pgsb.contentmanagement.service.RentHouseService;
import com.mk.xixili.pgsb.contentmanagement.util.PermissionCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "租房房屋信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-management/rent-house")
public class RentHouseController implements AllInOneController<RentHouse> {

    private final RentHouseService rentHouseService;
    private final PermissionCheckUtil permissionCheckUtil;

    @Override
    @ApiLog
    @Operation(summary = "AllInOneProcess")
    @PostMapping("/process")
    public ApiResult<?> allInOneProcess(@RequestBody @Valid GeneralQuery generalQuery) throws ServiceException, NoSuchMethodException {
        return AllInOneActionApiUtil.process(generalQuery, rentHouseService);
    }

    @Override
    @ApiLog
    @Operation(summary = "添加租房房屋信息")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody @Valid RentHouse rentHouse) {
        var userVo = permissionCheckUtil.checkUserAuth();
        rentHouse.setHouseId(StringUtil.generateNanoId(true, 10));
        rentHouse.setAuthorId(userVo.getUserId());
        rentHouse.setAuthorName(userVo.getUsername());
        rentHouse.setVisitCount(0);
        rentHouse.setGoodCount(0);
        AllInOneActionApiUtil.add(rentHouse, null, rentHouseService);
        return ResultUtil.success(rentHouse.getHouseId());
    }

    @ApiLog
    @Operation(summary = "添加房屋状态（点赞或访问量）")
    @GetMapping("/add-status")
    public ApiResult<Void> addStatus(@RequestParam String type, @RequestParam String houseId) {
        var queryWrapper = rentHouseService.lambdaQuery().eq(RentHouse::getHouseId, houseId);
        if (!queryWrapper.exists()) {
            ServiceExceptionType.OPERATION_FAIL.withMessage("房型不存在");
        }
        var one = queryWrapper.one();
        if ("good".equals(type)) {
            one.setGoodCount(one.getGoodCount() + 1);
        } else if ("visit".equals(type)) {
            one.setVisitCount(one.getVisitCount() + 1);
        } else {
            ServiceExceptionType.OPERATION_FAIL.withMessage("类型错误");
        }
        rentHouseService.updateById(one);
        return ResultUtil.success();
    }
}
