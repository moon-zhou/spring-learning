package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.constant.ExceptionHandleEnum;
import org.moonzhou.transaction.entity.Account;
import org.moonzhou.transaction.exception.AccountBiz1RuntimeException;
import org.moonzhou.transaction.exception.AccountBiz2RuntimeException;
import org.moonzhou.transaction.mapper.AccountMapper;
import org.moonzhou.transaction.param.AccountParam;
import org.moonzhou.transaction.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:03
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {


    @Override
    public Account getOneByParam(AccountParam accountParam) {

        return super.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getName, accountParam.getName())
                .eq(Account::getAge, accountParam.getAge()));
    }

    @Override
    public boolean deleteAll() {
        return super.remove(new QueryWrapper<>());
    }

    @Override
    public Long saveAccountWithoutTransAction(AccountParam accountParam, ConditionEnum condition) {
        Account account = getAccount(accountParam, condition);
        return account.getId();
    }

    @Transactional
    @Override
    public Long saveAccountTransAction(AccountParam accountParam, ConditionEnum condition) {
        Account account = getAccount(accountParam, condition);
        return account.getId();
    }

    @Transactional
    @Override
    public void saveAccountIncorrectTransAction(AccountParam accountParam, ExceptionHandleEnum exceptionHandleEnum) {
        try {
            Account account = new Account();
            BeanUtils.copyProperties(accountParam, account);

            boolean saveResult = super.save(account);
            log.info("AccountServiceImpl save result: {}.", saveResult);

            throw new RuntimeException("runtime exception after saving...");
        } catch (Exception e) {

            switch (exceptionHandleEnum) {
                // 默认走log
                default:
                case LOG:
                    // 处理异常但不继续往上抛出异常
                    log.error("incorrect transaction annotation, test exception: ", e);
                    break;
                case BIZ_NO_EXCEPTION:
                    // 处理业务，不往上抛异常
                    log.error("biz handle, test exception: ", e);
                    break;
                case THROW_RUNTIME_EXCEPTION:
                    throw new RuntimeException("test saveAccountIncorrectTransAction runtime exception!");
                case THROW_CUSTOM_RUNTIME_EXCEPTION:
                    throw new AccountBiz1RuntimeException("custom run time exception!");
                case THROW_THE_SAME_EXCEPTION:
                    throw new AccountBiz2RuntimeException(e);
            }

        }
    }

    private Account getAccount(AccountParam accountParam, ConditionEnum condition) {
        Account account = new Account();
        BeanUtils.copyProperties(accountParam, account);

        boolean saveResult = super.save(account);
        log.info("AccountServiceImpl save result: {}.", saveResult);

        // 如果需要运行时异常
        if (ConditionEnum.isRuntimeException(condition)) {
            throw new RuntimeException("runtime exception after saving...");
        }
        return account;
    }
}
