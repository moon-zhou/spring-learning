package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.moonzhou.transaction.entity.Sign;
import org.moonzhou.transaction.mapper.SignMapper;
import org.moonzhou.transaction.service.ISignService;
import org.springframework.stereotype.Service;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:03
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements ISignService {
}
