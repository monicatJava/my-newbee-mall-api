/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.api.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 訂單列表頁面VO
 */
@Data
public class NewBeeMallOrderListVO implements Serializable {

    private Long orderId;

    @ApiModelProperty("訂單號")
    private String orderNo;

    @ApiModelProperty("訂單價格")
    private Integer totalPrice;

    @ApiModelProperty("訂單支付方式")
    private Byte payType;

    @ApiModelProperty("訂單狀態碼")
    private Byte orderStatus;

    @ApiModelProperty("訂單狀態")
    private String orderStatusString;

    @ApiModelProperty("建立時間")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("訂單項列表")
    private List<NewBeeMallOrderItemVO> newBeeMallOrderItemVOS;
}
