/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: OfflineResult.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/8 16:03
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.moonzhou.offline.constant.ErrorCodeEnum;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Getter
@Setter
public class OfflineResult<T> extends CommonResult {

    private T data;

    public OfflineResult() {
    }

    public OfflineResult(ErrorCodeEnum errorCodeEnum) {
        super.setResponseCode(errorCodeEnum.getResponseCode());
        super.setResponseMessage(errorCodeEnum.getResponseMessage());
    }

    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();
        String resultJsonStr = null;
        try {
            resultJsonStr = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultJsonStr;
    }
}
