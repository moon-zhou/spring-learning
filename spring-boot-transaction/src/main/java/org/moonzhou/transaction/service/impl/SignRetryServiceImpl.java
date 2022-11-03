package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.SignRetry;
import org.moonzhou.transaction.mapper.SignRetryMapper;
import org.moonzhou.transaction.param.SignRetryParam;
import org.moonzhou.transaction.service.ISignRetryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:03
 */
@Slf4j
@Service
public class SignRetryServiceImpl extends ServiceImpl<SignRetryMapper, SignRetry> implements ISignRetryService {

    @Override
    public SignRetry getOneByParam(SignRetryParam signRetryParam) {
        return super.getOne(Wrappers.<SignRetry>lambdaQuery().eq(SignRetry::getUserNo, signRetryParam.getUserNo()));
    }

    @Override
    public List<SignRetry> getListByParam(SignRetryParam signRetryParam) {
        return super.list(Wrappers.<SignRetry>lambdaQuery().likeRight(SignRetry::getUserNo, signRetryParam.getUserNo()));
    }

    @Override
    public boolean deleteAll() {
        return super.remove(new QueryWrapper<>());
    }

    @Transactional
    @Override
    public Long saveSignRetry(SignRetryParam signRetryParam, ConditionEnum condition) {

        SignRetry signRetry = new SignRetry();
        BeanUtils.copyProperties(signRetryParam, signRetry);
        boolean save = super.save(signRetry);

        log.info("save sign retry result: {}.", save);

        if (ConditionEnum.isRuntimeException(condition)) {
            log.error("throw runtime exception, simulation biz exception...");
            throw new RuntimeException("runtime exception after saving...");
        }

        return save ? signRetry.getId() : 0L;
    }
}
