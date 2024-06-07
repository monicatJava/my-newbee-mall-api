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

/**
 * 收貨地址VO
 */
@Data
public class NewBeeMallUserAddressVO {

    @ApiModelProperty("地址id")
    private Long addressId;

    @ApiModelProperty("使用者id")
    private Long userId;

    @ApiModelProperty("收件人名稱")
    private String userName;

    @ApiModelProperty("收件人聯繫方式")
    private String userPhone;

    @ApiModelProperty("是否預設地址 0-不是 1-是")
    private Byte defaultFlag;

    @ApiModelProperty("省")
    private String provinceName;

    @ApiModelProperty("市")
    private String cityName;

    @ApiModelProperty("區/縣")
    private String regionName;

    @ApiModelProperty("詳細地址")
    private String detailAddress;
}
