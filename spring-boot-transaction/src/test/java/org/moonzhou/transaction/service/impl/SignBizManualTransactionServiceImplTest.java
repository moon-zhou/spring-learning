package org.moonzhou.transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.Sign;
import org.moonzhou.transaction.entity.SignRetry;
import org.moonzhou.transaction.param.SignBizParam;
import org.moonzhou.transaction.param.SignParam;
import org.moonzhou.transaction.param.SignRetryParam;
import org.moonzhou.transaction.service.ISignBizManualTransactionService;
import org.moonzhou.transaction.service.ISignRetryService;
import org.moonzhou.transaction.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author moon zhou
 * @version 1.0
 * @description: test manual transaction
 * @date 2022/11/4 15:08
 */
@Slf4j
@SpringBootTest
public class SignBizManualTransactionServiceImplTest {

    @Autowired
    private ISignBizManualTransactionService signBizManualTransactionService;

    @Autowired
    private ISignService signService;

    @Autowired
    private ISignRetryService signRetryService;

    @BeforeEach
    void beforeTest() {
        signBizManualTransactionService.deleteAll();
        log.info("before test class, delete data.");
    }

    @Order(1)
    @Test
    void signManualTransaction() {
        SignParam signParam = new SignParam("001", LocalDateTime.now());
        SignRetryParam signRetryParam = new SignRetryParam("001", LocalDateTime.now(), "{\"serialNo\":\"123\",\"userNo\":\"001\",\"signTime\":\"2022-11-03\",\"type\":\"01\"}", "01");
        SignBizParam signBizParam = new SignBizParam(signParam, signRetryParam);

        ////////////////////////test 1st case: save without transaction///////////////////////////
        // save without transaction
        Long saveCaseOne = signBizManualTransactionService.signManualTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        assertEquals(2, saveCaseOne);
        // assert save result
        Sign signCaseOne = signService.getOneByParam(signParam);
        SignRetry signRetryCaseOne = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseOne);
        assertNotNull(signRetryCaseOne);

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 2nd case: save first service exception///////////////////////////
        Long saveCaseTwo = signBizManualTransactionService.signManualTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL);
        assertEquals(0L, saveCaseTwo);
        Sign signCaseTwo = signService.getOneByParam(signParam);
        SignRetry signRetryCaseTwo = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseTwo); // because of exception rollback
        assertNull(signRetryCaseTwo); // not run because exception before this

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 3rd case: save first service normal, second service exception///////////////////////////
        Long saveCaseThree = signBizManualTransactionService.signManualTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION);
        assertEquals(0L, saveCaseThree);
        Sign signCaseThree = signService.getOneByParam(signParam);
        SignRetry signRetryCaseThree = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseThree); // not save because of next service exception
        assertNull(signRetryCaseThree); // not save successfully because of exception

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 4th case: save both sub service normal, main method exception///////////////////////////
        Long saveCaseFour = signBizManualTransactionService.signManualTransaction(signBizParam, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        assertEquals(0L, saveCaseFour);
        Sign signCaseFour = signService.getOneByParam(signParam);
        SignRetry signRetryCaseFour = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseFour);
        assertNull(signRetryCaseFour);

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();
    }

    @Order(2)
    @Test
    void signManualTransactionWithoutResult() {
        // init param
        SignParam signParam = new SignParam("002", LocalDateTime.now());
        SignRetryParam signRetryParam = new SignRetryParam("002", LocalDateTime.now(), "{\"serialNo\":\"123\",\"userNo\":\"002\",\"signTime\":\"2022-11-03\",\"type\":\"01\"}", "01");
        SignBizParam signBizParam = new SignBizParam(signParam, signRetryParam);

        ////////////////////////test 1st case: save without transaction///////////////////////////
        // save without transaction
        signBizManualTransactionService.signManualTransactionWithoutResult(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        // assert save result
        Sign signCaseOne = signService.getOneByParam(signParam);
        SignRetry signRetryCaseOne = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseOne);
        assertNotNull(signRetryCaseOne);

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 2nd case: save first service exception///////////////////////////
        signBizManualTransactionService.signManualTransactionWithoutResult(signBizParam, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL);
        Sign signCaseTwo = signService.getOneByParam(signParam);
        SignRetry signRetryCaseTwo = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseTwo); // because of exception rollback
        assertNull(signRetryCaseTwo); // not run because exception before this

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 3rd case: save first service normal, second service exception///////////////////////////
        signBizManualTransactionService.signManualTransactionWithoutResult(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION);
        Sign signCaseThree = signService.getOneByParam(signParam);
        SignRetry signRetryCaseThree = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseThree); // not save because of next service exception
        assertNull(signRetryCaseThree); // not save successfully because of exception

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();


        ////////////////////////test 4th case: save both sub service normal, main method exception///////////////////////////
        signBizManualTransactionService.signManualTransactionWithoutResult(signBizParam, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        Sign signCaseFour = signService.getOneByParam(signParam);
        SignRetry signRetryCaseFour = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseFour);
        assertNull(signRetryCaseFour);

        /////////////////////////// remove pre test step data
        signBizManualTransactionService.deleteAll();
    }
}
