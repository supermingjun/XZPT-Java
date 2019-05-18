package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/26 20:52
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private Integer resultCode;
    private String resultMsg;
    private T resultObject;

    public ResponseData() {
        this.resultCode = Constants.OK;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    /**
     * @param resultCode 返回错误码
     * @param resultMsg  返回消息
     * @param t          类型参数t
     * @return void
     * @author Murphy
     * @date 2019/4/26 21:17
     * @description 设置数据
     */
    public void putData(Integer resultCode, String resultMsg, T t) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultObject = t;
    }
}
