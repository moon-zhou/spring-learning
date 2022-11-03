package org.moonzhou.transaction.service;

import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.param.SignBizParam;

/**
 * @author moon zhou
 * @version 1.0
 * @description: sign biz service, combine multiple services
 * @date 2022/11/2 09:58
 */
public interface ISignBizService {

    void deleteAll();

    /**
     * 业务：签到
     * biz: sign
     * <p>
     * 签到入口方法无Transaction注解，但是调用两个单独的有注解方法，正常情况和异常情况（关注两个单独服务的事务传播机制，独立？）。
     *
     * @param signBizParam
     * @param mainCondition
     * @param signCondition
     * @param signRetryCondition
     */
    void sign(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition);

    /**
     * 业务：签到
     * biz: sign
     * <p>
     * 签到入口方法有Transaction注解，调用两个单独的有注解方法，正常情况和异常情况（关注两个单独服务的事务传播机制，独立？）
     *
     * @param signBizParam
     * @param mainCondition
     * @param signCondition
     * @param signRetryCondition
     */
    void signTransaction(SignBizParam signBizParam, ConditionEnum mainCondition, ConditionEnum signCondition, ConditionEnum signRetryCondition);
}
