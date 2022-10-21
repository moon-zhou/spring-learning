package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.Account;
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
