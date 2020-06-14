package org.moonzhou.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.constant.ErrorCodeEnum;
import org.moonzhou.exception.version1.customedexception.BaseException;
import org.moonzhou.exception.version1.customedexception.BusinessException;
import org.moonzhou.exception.version2.AppException;
import org.moonzhou.i18n.I18nService;
import org.moonzhou.result.BaseResult;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @Slf4j为lombok的，因为springboot默认已经关联了，直接依赖使用即可
 * @description
 * @date 2020/6/6 21:05
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private I18nService i18nService;

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = i18nService.getMessageDefault(code, e.getArgs());

        if (message == null || message.isEmpty()) {
            return e.getMessage();
        }

        return message;
    }

    public String getMessage(org.moonzhou.exception.version3.AppException e) {
        String message = i18nService.getMessageDefault(e.getCode());

        if (message == null || message.isEmpty()) {
            return e.getMessage();
        }

        return message;
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public BaseResult handleBusinessException(BaseException e) {
        log.error(e.getMessage(), e);

        return new BaseResult(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResult handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);

        return new BaseResult(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            // BindException.class,
            // MethodArgumentNotValidException.class
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public BaseResult handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        /*int code = CommonResponseEnum.SERVER_ERROR.getCode();
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(baseException);
            return new BaseResult(code, message);
        }*/

        return new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage());
    }


    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseResult handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private BaseResult wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

        }

//        return new BaseResult(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));
        return new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, msg.substring(2));
    }

    /**
     * 业务异常：第二个版本
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public BaseResult handleAppException(AppException e) {
        log.error(e.getMessage(), e);

        return new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage() + "版本二异常");
    }

    /**
     * 业务异常：第三个版本
     * @param e
     * @return
     */
    @ExceptionHandler(value = org.moonzhou.exception.version3.AppException.class)
    @ResponseBody
    public BaseResult handleAppException(org.moonzhou.exception.version3.AppException e) {
        log.error(e.getMessage(), e);

        return new BaseResult(e.getCode(), getMessage(e) + "版本三异常");
    }


    /**
     * 处理空指针异常，<b>且不论ExceptionHandler的顺序是啥，但凡是空指针，都进这个处理，不走Exception的</b>
     *
     * @param response
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public BaseResult globalNPEHandle(HttpServletRequest request, HttpServletResponse response, NullPointerException ex) {

        log.info("globalNPEHandle...");
        log.info("错误代码：" + response.getStatus());
        log.error("NullPointerException: ", ex);
        BaseResult result = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, "request error:" + response.getStatus(),
                "globalNPEHandle:" + ex.getMessage());
        return result;
    }

    /**
     * 全局异常，刨除明确处理的异常，其他异常都走这里
     *
     * @param response
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler
    public BaseResult globalExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        log.info("globalExceptionHandle...");
        log.info("错误代码：" + response.getStatus());
        BaseResult result = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, "request error:" + response.getStatus(),
                "globalExceptionHandle:" + ex.getMessage());
        return result;
    }
}
