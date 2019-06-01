package xz.fzu.handler;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.apache.commons.mail.EmailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import xz.fzu.exception.AbstractException;
import xz.fzu.util.Constants;
import xz.fzu.vo.ResponseVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Murphy
 * @date 2019/4/26 20:49
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @param httpServletResponse httpServletResponse
     * @param e                   异常实例
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/4/26 22:12
     * @description 默认的异常处理器
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO<String> defaultHandler(HttpServletResponse httpServletResponse, Exception e) {
        ResponseVO<String> responseObject = new ResponseVO<>();
        responseObject.setResultCode(Constants.INTERNAL_ERROR);
        responseObject.setResultObject("如果你看到这条消息，说明我还未处理此异常，" +
                "请先保存ResultMsg中的内容，并请向我反馈！谢谢！");
        if (httpServletResponse.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            responseObject.setResultCode(Constants.URL_NOT_FOUND);
            responseObject.setResultObject(null);
        } else if (httpServletResponse.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            responseObject.setResultCode(Constants.INTERNAL_ERROR);
        }
        responseObject.setResultMsg(e.getClass() + ":" + e.getMessage());
        printStack(e);

        return responseObject;
    }

    /**
     * @param e 异常实例
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/26 22:12
     * @description 处理Toke异常
     */
    @ExceptionHandler(value = SignatureVerificationException.class)
    @ResponseBody
    public ResponseVO defaultHandler(SignatureVerificationException e) {

        ResponseVO responseObject = new ResponseVO();
        responseObject.setResultCode(Constants.TOKEN_ERROR);
        responseObject.setResultMsg(e.getMessage());
        printStack(e);

        return responseObject;
    }

    /**
     * @param e 异常实例
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/26 22:12
     * @description 处理Toke异常
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public ResponseVO defaultHandler(NoHandlerFoundException e) {

        ResponseVO responseObject = new ResponseVO();
        responseObject.setResultCode(Constants.URL_NOT_FOUND);
        responseObject.setResultMsg("找不到Handler，请检查URL");
        printStack(e);

        return responseObject;
    }

    /**
     * @param e 异常实例
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/26 22:11
     * @description 处理MyException
     */
    @ExceptionHandler(value = AbstractException.class)
    @ResponseBody
    public ResponseVO myExceptionHandle(AbstractException e) {

        ResponseVO<String> responseObject = new ResponseVO<>();
        responseObject.putData(e.getErrorCode(), e.getMessage(), null);
//        printStack(e);
        StackTraceElement stackTraceElement = e.getStackTrace()[0];

        System.out.println("异常名：" + e.getClass());
        System.out.println("\t文件名：" + stackTraceElement.getFileName());
        ;
        System.out.println("\t函数名：" + stackTraceElement.getMethodName());
        ;
        System.out.println("\t行数：" + stackTraceElement.getLineNumber());
        ;

        return responseObject;
    }

    /**
     * @param e 异常实例
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 19:44
     * @description 邮件发送错误
     */
    @ExceptionHandler(value = EmailException.class)
    @ResponseBody
    public ResponseVO emailExceptionHandle(EmailException e) {

        ResponseVO<String> responseObject = new ResponseVO<>();
        responseObject.putData(Constants.SEND_EMAIL_ERROR, e.getMessage(), null);
        printStack(e);

        return responseObject;
    }

    private void printStack(Exception e) {
        e.printStackTrace();
    }
}
