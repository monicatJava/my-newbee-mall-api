/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.api.mall;

import io.swagger.annotations.*;
import ltd.newbee.mall.api.mall.param.MallUserLoginParam;
import ltd.newbee.mall.api.mall.param.MallUserRegisterParam;
import ltd.newbee.mall.api.mall.param.MallUserUpdateParam;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.config.annotation.TokenToMallUser;
import ltd.newbee.mall.api.mall.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.service.NewBeeMallUserService;
import ltd.newbee.mall.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(value = "v1", tags = "2.新蜂商城使用者操作相關介面")
@RequestMapping("/api/v1")
public class NewBeeMallPersonalAPI {

    @Resource
    private NewBeeMallUserService newBeeMallUserService;

    private static final Logger logger = LoggerFactory.getLogger(NewBeeMallPersonalAPI.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "登錄介面", notes = "返回token")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam) {
        if (!NumberUtil.isPhone(mallUserLoginParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = newBeeMallUserService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPasswordMd5());

        logger.info("login api,loginName={},loginResult={}", mallUserLoginParam.getLoginName(), loginResult);

        //登錄成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登錄失敗
        return ResultGenerator.genFailResult(loginResult);
    }


    @PostMapping("/user/logout")
    @ApiOperation(value = "登出介面", notes = "清除token")
    public Result<String> logout(@TokenToMallUser MallUser loginMallUser) {
        Boolean logoutResult = newBeeMallUserService.logout(loginMallUser.getUserId());

        logger.info("logout api,loginMallUser={}", loginMallUser.getUserId());

        //登出成功
        if (logoutResult) {
            return ResultGenerator.genSuccessResult();
        }
        //登出失敗
        return ResultGenerator.genFailResult("logout error");
    }


    @PostMapping("/user/register")
    @ApiOperation(value = "使用者註冊", notes = "")
    public Result register(@RequestBody @Valid MallUserRegisterParam mallUserRegisterParam) {
        if (!NumberUtil.isPhone(mallUserRegisterParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String registerResult = newBeeMallUserService.register(mallUserRegisterParam.getLoginName(), mallUserRegisterParam.getPassword());

        logger.info("register api,loginName={},loginResult={}", mallUserRegisterParam.getLoginName(), registerResult);

        //註冊成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //註冊失敗
        return ResultGenerator.genFailResult(registerResult);
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "修改使用者資訊", notes = "")
    public Result updateInfo(@RequestBody @ApiParam("使用者資訊") MallUserUpdateParam mallUserUpdateParam, @TokenToMallUser MallUser loginMallUser) {
        Boolean flag = newBeeMallUserService.updateUserInfo(mallUserUpdateParam, loginMallUser.getUserId());
        if (flag) {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        } else {
            //返回失敗
            Result result = ResultGenerator.genFailResult("修改失敗");
            return result;
        }
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "獲取使用者資訊", notes = "")
    public Result<NewBeeMallUserVO> getUserDetail(@TokenToMallUser MallUser loginMallUser) {
        //已登錄則直接返回
        NewBeeMallUserVO mallUserVO = new NewBeeMallUserVO();
        BeanUtil.copyProperties(loginMallUser, mallUserVO);
        return ResultGenerator.genSuccessResult(mallUserVO);
    }
}
