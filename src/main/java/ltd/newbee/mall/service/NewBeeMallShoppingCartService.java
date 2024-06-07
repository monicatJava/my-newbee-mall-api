/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.service;

import ltd.newbee.mall.api.mall.param.SaveCartItemParam;
import ltd.newbee.mall.api.mall.param.UpdateCartItemParam;
import ltd.newbee.mall.api.mall.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface NewBeeMallShoppingCartService {

    /**
     * 儲存商品至購物車中
     *
     * @param saveCartItemParam
     * @param userId
     * @return
     */
    String saveNewBeeMallCartItem(SaveCartItemParam saveCartItemParam, Long userId);

    /**
     * 修改購物車中的屬性
     *
     * @param updateCartItemParam
     * @param userId
     * @return
     */
    String updateNewBeeMallCartItem(UpdateCartItemParam updateCartItemParam, Long userId);

    /**
     * 獲取購物項詳情
     *
     * @param newBeeMallShoppingCartItemId
     * @return
     */
    NewBeeMallShoppingCartItem getNewBeeMallCartItemById(Long newBeeMallShoppingCartItemId);

    /**
     * 刪除購物車中的商品
     *
     *
     * @param shoppingCartItemId
     * @param userId
     * @return
     */
    Boolean deleteById(Long shoppingCartItemId, Long userId);

    /**
     * 獲取我的購物車中的列表數據
     *
     * @param newBeeMallUserId
     * @return
     */
    List<NewBeeMallShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId);

    /**
     * 根據userId和cartItemIds獲取對應的購物項記錄
     *
     * @param cartItemIds
     * @param newBeeMallUserId
     * @return
     */
    List<NewBeeMallShoppingCartItemVO> getCartItemsForSettle(List<Long> cartItemIds, Long newBeeMallUserId);

    /**
     * 我的購物車(分頁數據)
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyShoppingCartItems(PageQueryUtil pageUtil);
}
