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
public class GoodsCategoryEditParam {

    @ApiModelProperty("待修改分類id")
    @NotNull(message = "分類id不能為空")
    @Min(value = 1, message = "分類id不能為空")
    private Long categoryId;

    @ApiModelProperty("分類層級")
    @NotNull(message = "categoryLevel不能為空")
    @Min(value = 1, message = "分類級別最低為1")
    @Max(value = 3, message = "分類級別最高為3")
    private Byte categoryLevel;

    @ApiModelProperty("父類id")
    @NotNull(message = "parentId不能為空")
    @Min(value = 0, message = "parentId最低為0")
    private Long parentId;

    @ApiModelProperty("分類名稱")
    @NotEmpty(message = "categoryName不能為空")
    @Length(max = 16,message = "分類名稱過長")
    private String categoryName;

    @ApiModelProperty("排序值")
    @Min(value = 1, message = "categoryRank最低為1")
    @Max(value = 200, message = "categoryRank最高為200")
    @NotNull(message = "categoryRank不能為空")
    private Integer categoryRank;
}