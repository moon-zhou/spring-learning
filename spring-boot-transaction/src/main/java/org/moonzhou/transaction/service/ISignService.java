package org.moonzhou.transaction.service;

import org.moonzhou.transaction.constant.ConditionEnum;
import org.moonzhou.transaction.entity.Sign;
import org.moonzhou.transaction.param.SignParam;

import java.util.List;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 13:58
 */
public interface ISignService {

    Sign getOneByParam(SignParam signParam);

    List<Sign> getListByParam(SignParam signParam);

    boolean deleteAll();

    /**
     * save
     * @param signParam
     * @param condition
     * @return
     */
    Long saveSign(SignParam signParam, ConditionEnum condition);
}
