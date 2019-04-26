package xz.fzu.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xz.fzu.exception.MyException;
import xz.fzu.util.Constants;
import xz.fzu.vo.ResponseData;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Murphy
 * @date 2019/4/26 20:49
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultHandler(HttpServletResponse httpServletResponse, Exception e) {
        ResponseData responseObject = new ResponseData();
        responseObject.setResultCode(Constants.INTERNAL_ERROR);
        if (httpServletResponse.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            responseObject.setResultCode(Constants.URL_NOT_FOUND);
        } else if (httpServletResponse.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            responseObject.setResultCode(Constants.INTERNAL_ERROR);
        }
        responseObject.setResultMsg(e.getClass() + ":" + e.getMessage());
        return responseObject;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseData myExceptionHandle(MyException e) {
        ResponseData<String> responseObject = new ResponseData<String>();
        responseObject.putData(e.getErrorCode(), e.getMessage(), null);
        return responseObject;
    }
}
