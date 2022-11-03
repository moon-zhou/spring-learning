package org.moonzhou.transaction.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.param.SignBizParam;
import org.moonzhou.transaction.service.ISignBizService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author moon zhou
 * @version 1.0
 * @description: sign biz service, combine multiple services
 * @date 2022/11/2 10:00
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SignBizServiceImpl implements ISignBizService {

    private final SignServiceImpl signService;

    private final SignRetryServiceImpl signRetryService;

    @Override
    public void deleteAll() {
        signService.deleteAll();

        signRetryService.deleteAll();
    }

    @Override
    public void sign(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition) {

        Long signResult = signService.saveSign(signBizParam.getSignParam(), signCondition);

        Long signRetryResult = signRetryService.saveSignRetry(signBizParam.getSignRetryParam(), signRetryCondition);

        if (ConditionEnum.isRuntimeException(mainCondition)) {
            log.error("throw runtime exception, simulation biz exception...");
            throw new RuntimeException("runtime exception after saving...");
        }
    }

    @Transactional
    @Override
    public void signTransaction(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition) {
        Long signResult = signService.saveSign(signBizParam.getSignParam(), signCondition);

        Long signRetryResult = signRetryService.saveSignRetry(signBizParam.getSignRetryParam(), signRetryCondition);

        if (ConditionEnum.isRuntimeException(mainCondition)) {
            log.error("throw runtime exception, simulation biz exception...");
            throw new RuntimeException("runtime exception after saving...");
        }
    }
}
