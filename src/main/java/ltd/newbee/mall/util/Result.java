package ltd.newbee.mall.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //業務碼，比如成功、失敗、許可權不足等 code，可自行定義
    @ApiModelProperty("返回碼")
    private int resultCode;
    //返回資訊，後端在進行業務處理后返回給前端一個提示資訊，可自行定義
    @ApiModelProperty("返回資訊")
    private String message;
    //數據結果，泛型，可以是列表、單個對象、數字、布爾值等
    @ApiModelProperty("返回數據")
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
