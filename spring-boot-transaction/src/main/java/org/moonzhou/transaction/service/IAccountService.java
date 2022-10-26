package org.moonzhou.transaction.service;

import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.constant.ExceptionHandleEnum;
import org.moonzhou.transaction.entity.Account;
import org.moonzhou.transaction.param.AccountParam;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 13:58
 */
public interface IAccountService {

    /**
     * 根据条件查询数据
     * 查询类接口无事务，仅仅用于其他事务方法查询数据
     * @param accountParam
     * @return
     */
    Account getOneByParam(AccountParam accountParam);

    boolean deleteAll();

    /**
     * 保存，如果不加事务，出现异常，不影响数据入库
     * @param accountParam
     * @param condition
     * @return
     */
    Long saveAccountWithoutTransAction(AccountParam accountParam, ConditionEnum condition);

    /**
     * 保存，添加事务，出现异常，数据不入库
     * @param accountParam
     * @param condition
     * @return
     */
    Long saveAccountTransAction(AccountParam accountParam, ConditionEnum condition);

    /**
     * 不正确的使用TransAction注解
     * 1. 内部捕获异常之后，直接吞掉（只输出日志及其他处理），未继续网上抛出异常
     * @param accountParam
     * @param exceptionHandleEnum
     */
    void saveAccountIncorrectTransAction(AccountParam accountParam, ExceptionHandleEnum exceptionHandleEnum);
}
