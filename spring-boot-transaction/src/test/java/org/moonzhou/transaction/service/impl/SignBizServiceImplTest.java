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
import org.moonzhou.transaction.service.ISignBizService;
import org.moonzhou.transaction.service.ISignRetryService;
import org.moonzhou.transaction.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class SignBizServiceImplTest {

    @Autowired
    private ISignBizService signBizService;

    @Autowired
    private ISignService signService;

    @Autowired
    private ISignRetryService signRetryService;

    @BeforeEach
    void beforeTest() {
        signBizService.deleteAll();
        log.info("before test class, delete data.");
    }

    /**
     * 测试入口方法无事务注解，但是调用的多个子方法有事务，正常情况及异常情况的测试
     */
    @Order(1)
    @Test
    void sign() {

        SignParam signParam = new SignParam("001", LocalDateTime.now());
        SignRetryParam signRetryParam = new SignRetryParam("001", LocalDateTime.now(), "{\"serialNo\":\"123\",\"userNo\":\"001\",\"signTime\":\"2022-11-03\",\"type\":\"01\"}", "01");
        SignBizParam signBizParam = new SignBizParam(signParam, signRetryParam);

        ////////////////////////test 1st case: save without transaction///////////////////////////
        // save without transaction
        signBizService.sign(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        // assert save result
        Sign signCaseOne = signService.getOneByParam(signParam);
        SignRetry signRetryCaseOne = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseOne);
        assertNotNull(signRetryCaseOne);

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 2nd case: save first service exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.sign(signBizParam, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL);
        });
        Sign signCaseTwo = signService.getOneByParam(signParam);
        SignRetry signRetryCaseTwo = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseTwo); // because of exception rollback
        assertNull(signRetryCaseTwo); // not run because exception before this

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 3rd case: save first service normal, second service exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.sign(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION);
        });
        Sign signCaseThree = signService.getOneByParam(signParam);
        SignRetry signRetryCaseThree = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseThree); // normal save
        assertNull(signRetryCaseThree); // not save successfully because of exception

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 4th case: save both sub service normal, main method exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.sign(signBizParam, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        });
        Sign signCaseFour = signService.getOneByParam(signParam);
        SignRetry signRetryCaseFour = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseFour);
        assertNotNull(signRetryCaseFour);

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();
    }


    @Order(2)
    @Test
    void signTransaction() {
        // init param
        SignParam signParam = new SignParam("002", LocalDateTime.now());
        SignRetryParam signRetryParam = new SignRetryParam("002", LocalDateTime.now(), "{\"serialNo\":\"123\",\"userNo\":\"002\",\"signTime\":\"2022-11-03\",\"type\":\"01\"}", "01");
        SignBizParam signBizParam = new SignBizParam(signParam, signRetryParam);

        ////////////////////////test 1st case: save without transaction///////////////////////////
        // save without transaction
        signBizService.signTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        // assert save result
        Sign signCaseOne = signService.getOneByParam(signParam);
        SignRetry signRetryCaseOne = signRetryService.getOneByParam(signRetryParam);
        assertNotNull(signCaseOne);
        assertNotNull(signRetryCaseOne);

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 2nd case: save first service exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.signTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL);
        });
        Sign signCaseTwo = signService.getOneByParam(signParam);
        SignRetry signRetryCaseTwo = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseTwo); // because of exception rollback
        assertNull(signRetryCaseTwo); // not run because exception before this

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 3rd case: save first service normal, second service exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.signTransaction(signBizParam, ConditionEnum.NORMAL, ConditionEnum.NORMAL, ConditionEnum.RUNTIME_EXCEPTION);
        });
        Sign signCaseThree = signService.getOneByParam(signParam);
        SignRetry signRetryCaseThree = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseThree); // not save because of second service exception, both service use the same sql connection
        assertNull(signRetryCaseThree); // not save successfully because of exception

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();


        ////////////////////////test 4th case: save both sub service normal, main method exception///////////////////////////
        assertThrows(RuntimeException.class, () -> {
            signBizService.signTransaction(signBizParam, ConditionEnum.RUNTIME_EXCEPTION, ConditionEnum.NORMAL, ConditionEnum.NORMAL);
        });
        Sign signCaseFour = signService.getOneByParam(signParam);
        SignRetry signRetryCaseFour = signRetryService.getOneByParam(signRetryParam);
        assertNull(signCaseFour);
        assertNull(signRetryCaseFour);

        /////////////////////////// remove pre test step data
        signBizService.deleteAll();
    }
}