package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.moonzhou.transaction.entity.SignRetry;
import org.moonzhou.transaction.mapper.SignRetryMapper;
import org.moonzhou.transaction.service.ISignService;
import org.springframework.stereotype.Service;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:03
 */
@Service
public class SignRetryServiceImpl extends ServiceImpl<SignRetryMapper, SignRetry> implements ISignService {
}
