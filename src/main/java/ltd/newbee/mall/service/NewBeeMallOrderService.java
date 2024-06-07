/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.service;

import ltd.newbee.mall.api.mall.vo.NewBeeMallOrderDetailVO;
import ltd.newbee.mall.api.mall.vo.NewBeeMallOrderItemVO;
import ltd.newbee.mall.api.mall.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.entity.MallUserAddress;
import ltd.newbee.mall.entity.NewBeeMallOrder;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface NewBeeMallOrderService {
    /**
     * 獲取訂單詳情
     *
     * @param orderId
     * @return
     */
    NewBeeMallOrderDetailVO getOrderDetailByOrderId(Long orderId);

    /**
     * 獲取訂單詳情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    NewBeeMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * 我的訂單列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyOrders(PageQueryUtil pageUtil);

    /**
     * 手動取消訂單
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String cancelOrder(String orderNo, Long userId);

    /**
     * 確認收貨
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String finishOrder(String orderNo, Long userId);

    String paySuccess(String orderNo, int payType);

    String saveOrder(MallUser loginMallUser, MallUserAddress address, List<NewBeeMallShoppingCartItemVO> itemsForSave);

    /**
     * 後臺分頁
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallOrdersPage(PageQueryUtil pageUtil);

    /**
     * 訂單資訊修改
     *
     * @param newBeeMallOrder
     * @return
     */
    String updateOrderInfo(NewBeeMallOrder newBeeMallOrder);

    /**
     * 配貨
     *
     * @param ids
     * @return
     */
    String checkDone(Long[] ids);

    /**
     * 出庫
     *
     * @param ids
     * @return
     */
    String checkOut(Long[] ids);

    /**
     * 關閉訂單
     *
     * @param ids
     * @return
     */
    String closeOrder(Long[] ids);

    List<NewBeeMallOrderItemVO> getOrderItems(Long orderId);
}
