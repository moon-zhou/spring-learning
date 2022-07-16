package org.moonzhou.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.moonzhou.mybatisplus.model.entity.UserWageEncryptionResultMap;

import java.util.List;

/**
 * @author moon zhou
 */
public interface UserWageEncryptionResultMapMapper extends BaseMapper<UserWageEncryptionResultMap> {

    int insertSelf(UserWageEncryptionResultMap param);

    List<UserWageEncryptionResultMap> selectAll();
}
