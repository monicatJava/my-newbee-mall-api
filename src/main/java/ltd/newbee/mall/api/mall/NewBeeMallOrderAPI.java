/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.api.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ltd.newbee.mall.api.mall.param.SaveOrderParam;
import ltd.newbee.mall.api.mall.vo.NewBeeMallOrderDetailVO;
import ltd.newbee.mall.api.mall.vo.NewBeeMallOrderListVO;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.config.annotation.TokenToMallUser;
import ltd.newbee.mall.api.mall.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.entity.MallUserAddress;
import ltd.newbee.mall.service.NewBeeMallOrderService;
import ltd.newbee.mall.service.NewBeeMallShoppingCartService;
import ltd.newbee.mall.service.NewBeeMallUserAddressService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1", tags = "7.新蜂商城訂單操作相關介面")
@RequestMapping("/api/v1")
public class NewBeeMallOrderAPI {

    @Resource
    private NewBeeMallShoppingCartService newBeeMallShoppingCartService;
    @Resource
    private NewBeeMallOrderService newBeeMallOrderService;
    @Resource
    private NewBeeMallUserAddressService newBeeMallUserAddressService;

    @PostMapping("/saveOrder")
    @ApiOperation(value = "產生訂單介面", notes = "傳參為地址id和待結算的購物項id陣列")
    public Result<String> saveOrder(@ApiParam(value = "訂單參數") @RequestBody SaveOrderParam saveOrderParam, @TokenToMallUser MallUser loginMallUser) {
        int priceTotal = 0;
        if (saveOrderParam == null || saveOrderParam.getCartItemIds() == null || saveOrderParam.getAddressId() == null) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        if (saveOrderParam.getCartItemIds().length < 1) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        List<NewBeeMallShoppingCartItemVO> itemsForSave = newBeeMallShoppingCartService.getCartItemsForSettle(Arrays.asList(saveOrderParam.getCartItemIds()), loginMallUser.getUserId());
        if (CollectionUtils.isEmpty(itemsForSave)) {
            //無數據
            NewBeeMallException.fail("參數異常");
        } else {
            //總價
            for (NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO : itemsForSave) {
                priceTotal += newBeeMallShoppingCartItemVO.getGoodsCount() * newBeeMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                NewBeeMallException.fail("價格異常");
            }
            MallUserAddress address = newBeeMallUserAddressService.getMallUserAddressById(saveOrderParam.getAddressId());
            if (!loginMallUser.getUserId().equals(address.getUserId())) {
                return ResultGenerator.genFailResult(ServiceResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
            }
            //儲存訂單並返回訂單號
            String saveOrderResult = newBeeMallOrderService.saveOrder(loginMallUser, address, itemsForSave);
            Result result = ResultGenerator.genSuccessResult();
            result.setData(saveOrderResult);
            return result;
        }
        return ResultGenerator.genFailResult("產生訂單失敗");
    }

    @GetMapping("/order/{orderNo}")
    @ApiOperation(value = "訂單詳情介面", notes = "傳參為訂單號")
    public Result<NewBeeMallOrderDetailVO> orderDetailPage(@ApiParam(value = "訂單號") @PathVariable("orderNo") String orderNo, @TokenToMallUser MallUser loginMallUser) {
        return ResultGenerator.genSuccessResult(newBeeMallOrderService.getOrderDetailByOrderNo(orderNo, loginMallUser.getUserId()));
    }

    @GetMapping("/order")
    @ApiOperation(value = "訂單列表介面", notes = "傳參為頁碼")
    public Result<PageResult<List<NewBeeMallOrderListVO>>> orderList(@ApiParam(value = "頁碼") @RequestParam(required = false) Integer pageNumber,
                            @ApiParam(value = "訂單狀態:0.待支付 1.待確認 2.待發貨 3:已發貨 4.交易成功") @RequestParam(required = false) Integer status,
                            @TokenToMallUser MallUser loginMallUser) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("userId", loginMallUser.getUserId());
        params.put("orderStatus", status);
        params.put("page", pageNumber);
        params.put("limit", Constants.ORDER_SEARCH_PAGE_LIMIT);
        //封裝分頁請求參數
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallOrderService.getMyOrders(pageUtil));
    }

    @PutMapping("/order/{orderNo}/cancel")
    @ApiOperation(value = "訂單取消介面", notes = "傳參為訂單號")
    public Result cancelOrder(@ApiParam(value = "訂單號") @PathVariable("orderNo") String orderNo, @TokenToMallUser MallUser loginMallUser) {
        String cancelOrderResult = newBeeMallOrderService.cancelOrder(orderNo, loginMallUser.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(cancelOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(cancelOrderResult);
        }
    }

    @PutMapping("/order/{orderNo}/finish")
    @ApiOperation(value = "確認收貨介面", notes = "傳參為訂單號")
    public Result finishOrder(@ApiParam(value = "訂單號") @PathVariable("orderNo") String orderNo, @TokenToMallUser MallUser loginMallUser) {
        String finishOrderResult = newBeeMallOrderService.finishOrder(orderNo, loginMallUser.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(finishOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(finishOrderResult);
        }
    }

    @GetMapping("/paySuccess")
    @ApiOperation(value = "模擬支付成功回撥的介面", notes = "傳參為訂單號和支付方式")
    public Result paySuccess(@ApiParam(value = "訂單號") @RequestParam("orderNo") String orderNo, @ApiParam(value = "支付方式") @RequestParam("payType") int payType) {
        String payResult = newBeeMallOrderService.paySuccess(orderNo, payType);
        if (ServiceResultEnum.SUCCESS.getResult().equals(payResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(payResult);
        }
    }

}
