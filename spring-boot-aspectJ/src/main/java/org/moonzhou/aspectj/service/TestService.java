package org.moonzhou.aspectj.service;

import org.moonzhou.aspectj.dto.ProcessDto;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:04
 **/
public interface TestService {
    /**
     *
     * @param id
     * @return
     */
    ProcessDto findProcessById(String id);

    /**
     * 生成固定process
     * @return
     */
    ProcessDto process();
}
