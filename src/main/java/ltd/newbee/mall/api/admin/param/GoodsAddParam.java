/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.api.admin.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GoodsAddParam {

    @ApiModelProperty("商品名稱")
    @NotEmpty(message = "商品名稱不能為空")
    @Length(max = 128,message = "商品名稱內容過長")
    private String goodsName;

    @ApiModelProperty("商品簡介")
    @NotEmpty(message = "商品簡介不能為空")
    @Length(max = 200,message = "商品簡介內容過長")
    private String goodsIntro;

    @ApiModelProperty("分類id")
    @NotNull(message = "分類id不能為空")
    @Min(value = 1, message = "分類id最低為1")
    private Long goodsCategoryId;

    @ApiModelProperty("商品主圖")
    @NotEmpty(message = "商品主圖不能為空")
    private String goodsCoverImg;

    @ApiModelProperty("originalPrice")
    @NotNull(message = "originalPrice不能為空")
    @Min(value = 1, message = "originalPrice最低為1")
    @Max(value = 1000000, message = "originalPrice最高為1000000")
    private Integer originalPrice;

    @ApiModelProperty("sellingPrice")
    @NotNull(message = "sellingPrice不能為空")
    @Min(value = 1, message = "sellingPrice最低為1")
    @Max(value = 1000000, message = "sellingPrice最高為1000000")
    private Integer sellingPrice;

    @ApiModelProperty("庫存")
    @NotNull(message = "庫存不能為空")
    @Min(value = 1, message = "庫存最低為1")
    @Max(value = 100000, message = "庫存最高為100000")
    private Integer stockNum;

    @ApiModelProperty("商品標籤")
    @NotEmpty(message = "商品標籤不能為空")
    @Length(max = 16,message = "商品標籤內容過長")
    private String tag;

    private Byte goodsSellStatus;

    @ApiModelProperty("商品詳情")
    @NotEmpty(message = "商品詳情不能為空")
    private String goodsDetailContent;
}