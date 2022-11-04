package org.moonzhou.transaction.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.param.SignBizParam;
import org.moonzhou.transaction.service.ISignBizManualTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author moon zhou
 * @version 1.0
 * @description: sign biz service, combine multiple services, manual transaction
 * @date 2022/11/4 11:33
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SignBizManualTransactionServiceImpl implements ISignBizManualTransactionService {

    private final SignServiceImpl signService;

    private final SignRetryServiceImpl signRetryService;

    private final TransactionTemplate transactionTemplate;

    @Override
    public void deleteAll() {
        signService.deleteAll();

        signRetryService.deleteAll();
    }

    @Override
    public Long signManualTransaction(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition) {

        Long execute = transactionTemplate.execute(status -> {
            try {
                // biz code
                Long signResult = signService.saveSign(signBizParam.getSignParam(), signCondition);

                Long signRetryResult = signRetryService.saveSignRetry(signBizParam.getSignRetryParam(), signRetryCondition);

                if (ConditionEnum.isRuntimeException(mainCondition)) {
                    log.error("throw runtime exception, simulation biz exception...");
                    throw new RuntimeException("runtime exception after saving...");
                }

                log.info("save manual transaction result: {}.", signResult + signRetryResult);
                return signResult + signRetryResult;
            } catch (Exception e) {
                log.error("save exception, manual rollback.", e);

                // rollback
                status.setRollbackOnly();
                return 0L;
            }
        });

        return execute;
    }

    @Override
    public void signManualTransactionWithoutResult(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition) {
        transactionTemplate.executeWithoutResult(status -> {
            try {
                // biz code
                Long signResult = signService.saveSign(signBizParam.getSignParam(), signCondition);

                Long signRetryResult = signRetryService.saveSignRetry(signBizParam.getSignRetryParam(), signRetryCondition);

                if (ConditionEnum.isRuntimeException(mainCondition)) {
                    log.error("throw runtime exception, simulation biz exception...");
                    throw new RuntimeException("runtime exception after saving...");
                }

                log.info("save manual transaction result: {}.", signResult + signRetryResult);
            } catch (Exception e) {
                log.error("save exception, manual rollback.", e);

                // rollback
                status.setRollbackOnly();
            }
        });
    }
}
