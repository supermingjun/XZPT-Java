package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/26 20:52
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseVO<T> {
    private Integer resultCode;
    private String resultMsg;
    private T resultObject;

    public ResponseVO() {
        this.resultCode = Constants.OK;
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
