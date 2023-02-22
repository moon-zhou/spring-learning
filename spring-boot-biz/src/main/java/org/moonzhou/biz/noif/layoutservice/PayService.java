package org.moonzhou.biz.noif.layoutservice;

import org.moonzhou.biz.noif.annotation.PayCode;
import org.moonzhou.biz.noif.constant.PayEnum;
import org.moonzhou.biz.noif.service.pay.IPay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存pay的实现类，同时通过map来替代if判断
 * https://juejin.cn/post/7122400370071961631
 * @author moon zhou
 */
@Service
public class PayService implements ApplicationListener<ContextRefreshedEvent> {
     private static Map<String, IPay> payMap = null;
     
     @Override
     public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {  
         ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
         Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);
        
         if (beansWithAnnotation != null) {  
             payMap = new HashMap<>();
             beansWithAnnotation.forEach((key, value) ->{  
                 String bizType = value.getClass().getAnnotation(PayCode.class).value().getCode();
                 payMap.put(bizType, (IPay) value);  
             });  
         }  
     }  
    
     public void pay(PayEnum payEnum) {
        payMap.get(payEnum.getCode()).pay();
     }  
}