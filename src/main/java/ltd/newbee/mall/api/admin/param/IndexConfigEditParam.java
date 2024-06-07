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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class IndexConfigEditParam {

    @ApiModelProperty("待修改配置id")
    @NotNull(message = "configId不能為空")
    @Min(value = 1, message = "configId不能為空")
    private Long configId;

    @ApiModelProperty("配置的名稱")
    @NotEmpty(message = "configName不能為空")
    private String configName;

    @ApiModelProperty("配置類別")
    @NotNull(message = "configType不能為空")
    @Min(value = 1, message = "configType最小為1")
    @Max(value = 5, message = "configType最大為5")
    private Byte configType;

    @ApiModelProperty("商品id")
    @NotNull(message = "商品id不能為空")
    @Min(value = 1, message = "商品id不能為空")
    private Long goodsId;

    @ApiModelProperty("排序值")
    @Min(value = 1, message = "configRank最低為1")
    @Max(value = 200, message = "configRank最高為200")
    @NotNull(message = "configRank不能為空")
    private Integer configRank;
}