package org.moonzhou.aspectj.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.aspectj.constant.DictEnum;
import org.moonzhou.aspectj.dto.ProcessDto;
import org.moonzhou.aspectj.dto.UserDto;
import org.moonzhou.aspectj.service.TestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:04
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public ProcessDto findProcessById(String id) {

        log.debug("find process by id, param is: {}", id);

        // 模拟查询数据库耗时
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            log.error("simulation db query error: ", e);
        }

        ProcessDto processDto = new ProcessDto(UUID.randomUUID().toString(), LocalDateTime.now(),
                DictEnum.PROCESS_STATUS_SUBMITTED.getCode(), DictEnum.PRIORITY_LEVEL_HIGH.getCode());

        return processDto;
    }

    @Override
    public ProcessDto process() {

        // 模拟查询数据库耗时
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            log.error("simulation db query error: ", e);
        }

        return new ProcessDto(UUID.randomUUID().toString(), LocalDateTime.now(),
                DictEnum.PROCESS_STATUS_SUBMITTED.getCode(), DictEnum.PRIORITY_LEVEL_HIGH.getCode());
    }

    @Override
    public UserDto user() {
        // 总监
        UserDto director = new UserDto("Wang Wu", DictEnum.POSITION_LEVEL_DIRECTOR.getCode(), null);

        // 经理
        UserDto manager = new UserDto("Li Si", DictEnum.POSITION_LEVE_MANAGER.getCode(), director);

        // 普通员工
        UserDto staff = new UserDto("Zhang San", DictEnum.POSITION_LEVEL_GENERAL.getCode(), manager);

        return staff;
    }
}
