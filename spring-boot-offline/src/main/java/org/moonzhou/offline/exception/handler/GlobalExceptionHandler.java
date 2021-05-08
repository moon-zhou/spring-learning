/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: GlobalExceptionHandler.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/8 15:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.offline.constant.ErrorCodeEnum;
import org.moonzhou.offline.dto.OfflineResult;
import org.moonzhou.offline.exception.AjaxOfflineException;
import org.moonzhou.offline.exception.FormOfflineException;
import org.moonzhou.offline.exception.OfflineException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述: 下线异常统一处理类<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ajax请求下线异常监听处理
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = AjaxOfflineException.class)
    @ResponseBody
    public OfflineResult handleAjaxOfflineException(OfflineException e) {
        log.info("hand ajax offline exception...");
//        log.error(e.getMessage(), e);

        OfflineResult offlineResult = new OfflineResult<String>(ErrorCodeEnum.OFFLINE_ERROR);
        offlineResult.setData(e.getMessage());

        return offlineResult;
    }

    /**
     * form请求下线异常监听处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = FormOfflineException.class)
    public ModelAndView handFormOfflineException(OfflineException e) {
        log.info("hand form offline exception...");

        ModelAndView offlinePage = new ModelAndView("offline");

        OfflineResult offlineResult = new OfflineResult<String>(ErrorCodeEnum.OFFLINE_ERROR);
        offlineResult.setData(e.getMessage());

        // toString为lombok生成
        offlinePage.addObject("message", offlineResult.toString());

        return offlinePage;
    }
}
