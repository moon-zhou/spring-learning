/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: PrdHelloServiceImpl.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/15 16:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.service.impl;

import org.moonzhou.profile.service.HelloService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
@Profile("dev")
public class DevHelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello dev";
    }
}
