package org.moonzhou.transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.constant.ExceptionHandleEnum;
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
        // 断言保存后有数据
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

        // 运行时异常，但是因为添加事务注解，则无法正常保存
        AccountParam exceptionAccountParam = new AccountParam("002", "moon", 19, "ayimin1989@163.com", "Bank member");
        assertThrows(RuntimeException.class, () -> {
            accountService.saveAccountTransAction(exceptionAccountParam, ConditionEnum.RUNTIME_EXCEPTION);
        });
        // 断言保存后无数据
        Account exceptionAccount = accountService.getOneByParam(exceptionAccountParam);
        assertNull(exceptionAccount);
    }

    /**
     * 测试方法有事务注解，如果运行时异常被catch之后，未继续向上抛运行时异常，则不会回滚事务，数据可以被保存
     */
    @Order(3)
    @Test
    void saveAccountIncorrectTransAction() {
        // 保存数据，遇到异常不继续往上抛，异常不回滚，数据入库
        AccountParam normalAccountParam = new AccountParam("001", "moon", 18, "ayimin1989@163.com", "Financial member");
        accountService.saveAccountIncorrectTransAction(normalAccountParam, ExceptionHandleEnum.LOG);
        // 断言数据已入库
        Account normalAccount = accountService.getOneByParam(normalAccountParam);
        assertNotNull(normalAccount);

        // 保存数据，捕获异常之后，继续往上抛异常
        AccountParam exceptionAccountParam = new AccountParam("002", "moon", 19, "ayimin1989@163.com", "Bank member");
        assertThrows(RuntimeException.class, () -> {
            accountService.saveAccountIncorrectTransAction(exceptionAccountParam, ExceptionHandleEnum.THROW_RUNTIME_EXCEPTION);
        });
        // 断言事务回滚，数据未入库
        Account exceptionAccount = accountService.getOneByParam(exceptionAccountParam);
        assertNull(exceptionAccount);
    }
}