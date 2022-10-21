package org.moonzhou.transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.Account;
import org.moonzhou.transaction.param.AccountParam;
import org.moonzhou.transaction.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private IAccountService accountService;

    @BeforeEach
    void beforeTest() {
        boolean deleteAll = accountService.deleteAll();
        log.info("before test class, delete data: {}.", deleteAll);
    }

    /**
     * 测试方法无事务注解，正常和异常均可以保存
     */
    @Order(1)
    @Test
    void saveAccountWithoutTransAction() {
        // 正常情况，正常保存
        AccountParam normalAccountParam = new AccountParam("001", "moon", 18, "ayimin1989@163.com", "Financial member");
        Long normalSave = accountService.saveAccountWithoutTransAction(normalAccountParam, ConditionEnum.NORMAL);
        assertTrue(normalSave > 0L);

        // 运行时异常，但是因为没有添加事务注解，依然可以正常保存
        AccountParam exceptionAccountParam = new AccountParam("002", "moon", 19, "ayimin1989@163.com", "Bank member");
        assertThrows(RuntimeException.class, () -> {
            accountService.saveAccountWithoutTransAction(exceptionAccountParam, ConditionEnum.RUNTIME_EXCEPTION);
        });

        Account exceptionAccount = accountService.getOneByParam(exceptionAccountParam);
        assertNotNull(exceptionAccount);
    }

    /**
     * 测试方法有事务注解，正常可以保存，异常不能保存
     */
    @Order(2)
    @Test
    void saveAccountTransAction() {
        // 正常情况，正常保存
        AccountParam normalAccountParam = new AccountParam("001", "moon", 18, "ayimin1989@163.com", "Financial member");
        Long normalSave = accountService.saveAccountTransAction(normalAccountParam, ConditionEnum.NORMAL);
        assertTrue(normalSave > 0L);

        // 运行时异常，但是因为没有添加事务注解，依然可以正常保存
        AccountParam exceptionAccountParam = new AccountParam("002", "moon", 19, "ayimin1989@163.com", "Bank member");
        assertThrows(RuntimeException.class, () -> {
            accountService.saveAccountTransAction(exceptionAccountParam, ConditionEnum.RUNTIME_EXCEPTION);
        });

        Account exceptionAccount = accountService.getOneByParam(exceptionAccountParam);
        assertNull(exceptionAccount);
    }
}