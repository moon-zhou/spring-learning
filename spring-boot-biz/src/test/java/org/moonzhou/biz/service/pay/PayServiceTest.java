package org.moonzhou.biz.service.pay;

import org.junit.jupiter.api.Test;
import org.moonzhou.biz.constant.PayEnum;
import org.moonzhou.biz.layoutservice.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单元测试
 * https://www.cnblogs.com/myitnews/p/12330297.html
 */
@SpringBootTest
public class PayServiceTest {

    @Autowired
    private PayService payService;

    @Test
    public void test() {

        payService.pay(PayEnum.ALI_PAY);

        payService.pay(PayEnum.WE_CHAT_PAY);

        payService.pay(PayEnum.JINGDONG_PAY);

        payService.pay(PayEnum.SUNING_PAY);
    }

}