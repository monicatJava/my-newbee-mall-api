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
import java.util.List;

/**
 * 首頁分類數據VO(第二級)
 */
@Data
public class SecondLevelCategoryVO implements Serializable {

    @ApiModelProperty("目前二級分類id")
    private Long categoryId;

    @ApiModelProperty("父級分類id")
    private Long parentId;

    @ApiModelProperty("目前分類級別")
    private Byte categoryLevel;

    @ApiModelProperty("目前二級分類名稱")
    private String categoryName;

    @ApiModelProperty("三級分類列表")
    private List<ThirdLevelCategoryVO> thirdLevelCategoryVOS;
}
