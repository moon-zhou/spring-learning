package org.moonzhou.transaction.service;

import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.param.SignBizParam;

/**
 * @author moon zhou
 * @version 1.0
 * @description: sign biz service, combine multiple services
 * @date 2022/11/2 09:58
 */
public interface ISignBizManualTransactionService {

    void deleteAll();

    /**
     * 业务：签到
     * biz: sign
     * <p>
     * 手动进行事务管理，带有返回值
     *
     * @param signBizParam
     * @param mainCondition
     * @param signCondition
     * @param signRetryCondition
     */
    Long signManualTransaction(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition);

    /**
     * 业务：签到
     * biz: sign
     * <p>
     * 手动进行事务管理，带有返回值
     *
     * @param signBizParam
     * @param mainCondition
     * @param signCondition
     * @param signRetryCondition
     */
    void signManualTransactionWithoutResult(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition);
}
