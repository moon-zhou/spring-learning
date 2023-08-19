package org.moonzhou.springcache.service;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.springcache.dto.BizDto;
import org.moonzhou.springcache.param.BizParam;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/8/19 21:19
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "BizService")
public class BizService {

    @Cacheable(value = "bizDto", key = "#id")
    public BizDto getById(Long id) {

        log.info("mock biz, getById from db...");
        BizDto bizDto = new BizDto(id);
        bizDto.setName("moon zhou");
        bizDto.setAge(18);
        bizDto.setBirthDay(LocalDate.of(1989, 10, 6));
        bizDto.setCreateTime(LocalDateTime.now());

        return bizDto;
    }

    @CachePut(cacheNames = "BizDto", key = "#bizParam.id")
    public void updateById(BizParam bizParam) {
        // 更新数据库中的产品信息
    }

    @CacheEvict(cacheNames = "BizDto", key = "#id")
    public void deleteProduct(String id) {
        // 从数据库中删除产品信息

    }
}
