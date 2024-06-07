/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.api.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品詳情頁VO
 */
@Data
public class NewBeeMallGoodsDetailVO implements Serializable {

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("商品名稱")
    private String goodsName;

    @ApiModelProperty("商品簡介")
    private String goodsIntro;

    @ApiModelProperty("商品圖片地址")
    private String goodsCoverImg;

    @ApiModelProperty("商品價格")
    private Integer sellingPrice;

    @ApiModelProperty("商品標籤")
    private String tag;

    @ApiModelProperty("商品圖片")
    private String[] goodsCarouselList;

    @ApiModelProperty("商品原價")
    private Integer originalPrice;

    @ApiModelProperty("商品詳情欄位")
    private String goodsDetailContent;
}
