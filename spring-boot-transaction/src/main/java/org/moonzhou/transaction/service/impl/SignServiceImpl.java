package org.moonzhou.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.Sign;
import org.moonzhou.transaction.mapper.SignMapper;
import org.moonzhou.transaction.param.SignParam;
import org.moonzhou.transaction.service.ISignService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:03
 */
@Slf4j
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements ISignService {

    @Override
    public Sign getOneByParam(SignParam signParam) {
        return super.getOne(Wrappers.<Sign>lambdaQuery().eq(Sign::getUserNo, signParam.getUserNo()));
    }

    @Override
    public List<Sign> getListByParam(SignParam signParam) {
        return super.list(Wrappers.<Sign>lambdaQuery().likeRight(Sign::getUserNo, signParam.getUserNo()));
    }

    @Override
    public boolean deleteAll() {
        return super.remove(new QueryWrapper<>());
    }

    @Transactional
    @Override
    public Long saveSign(SignParam signParam, ConditionEnum condition) {
        Sign sign = new Sign();
        BeanUtils.copyProperties(signParam, sign);
        boolean save = super.save(sign);

        log.info("save sign result: {}.", save);

        if (ConditionEnum.isRuntimeException(condition)) {
            log.error("throw runtime exception, simulation biz exception...");
            throw new RuntimeException("runtime exception after saving...");
        }

        return save ? sign.getId() : 0L;
    }
}
