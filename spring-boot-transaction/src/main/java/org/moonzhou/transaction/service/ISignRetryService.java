package org.moonzhou.transaction.service;

import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.SignRetry;
import org.moonzhou.transaction.param.SignRetryParam;

import java.util.List;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 13:58
 */
public interface ISignRetryService {

    SignRetry getOneByParam(SignRetryParam signRetryParam);

    List<SignRetry> getListByParam(SignRetryParam signRetryParam);

    boolean deleteAll();

    /**
     * save without transaction
     * @param signRetryParam
     * @param condition
     * @return
     */
    Long saveSignRetry(SignRetryParam signRetryParam, ConditionEnum condition);

    /**
     * save with transaction
     * @param signRetryParam
     * @param condition
     * @return
     */
    Long saveSignRetryTransaction(SignRetryParam signRetryParam, ConditionEnum condition);
}
