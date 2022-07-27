package org.moonzhou.biz.service.pay.impl;

import org.moonzhou.biz.annotation.PayCode;
import org.moonzhou.biz.constant.PayEnum;
import org.moonzhou.biz.service.pay.IPay;
import org.springframework.stereotype.Service;

@PayCode(value = PayEnum.JINGDONG_PAY)
@Service
public class JingDongPay implements IPay {
     @Override
     public void pay() {  
        System.out.println("===发起京东支付===");  
     }  
}