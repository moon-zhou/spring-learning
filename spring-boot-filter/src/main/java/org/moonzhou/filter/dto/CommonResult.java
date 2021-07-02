/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: CommonResult.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/7 16:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.dto;

import lombok.Data;

/**
 * 功能描述: 抽象返回json数据的基类<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class CommonResult {

    private String responseCode = "0000";

    private String responseMessage = "success";

}
