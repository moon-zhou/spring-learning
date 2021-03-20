/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MonitorService.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 15:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.service;

import org.moonzhou.xss.dao.MonitorDataMapper;
import org.moonzhou.xss.dmo.MonitorDataDmo;
import org.moonzhou.xss.dto.MonitorData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class MonitorService {

    @Autowired
    private MonitorDataMapper monitorDataMapper;

    public List<MonitorData> getAllMonitorData() {

        List<MonitorData> monitorDatas = new ArrayList<>();
        List<MonitorDataDmo> monitorDataDmos = monitorDataMapper.findAll();

        for (MonitorDataDmo monitorDataDmo : monitorDataDmos) {
            MonitorData monitorData = new MonitorData();
            BeanUtils.copyProperties(monitorDataDmo, monitorData);

            monitorDatas.add(monitorData);
        }

        return monitorDatas;
    }
}
