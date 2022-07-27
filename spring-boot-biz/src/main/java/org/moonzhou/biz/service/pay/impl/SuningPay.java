package org.moonzhou.biz.service.pay.impl;

import org.moonzhou.biz.annotation.PayCode;
import org.moonzhou.biz.constant.PayEnum;
import org.moonzhou.biz.service.pay.IPay;
import org.springframework.stereotype.Service;

@PayCode(value = PayEnum.SUNING_PAY)
@Service
public class SuningPay implements IPay {

    @Override
    public void pay() {
        System.out.println("===发起苏宁支付===");
    }
}