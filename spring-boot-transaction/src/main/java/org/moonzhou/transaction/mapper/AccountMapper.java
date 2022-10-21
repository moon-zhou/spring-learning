package org.moonzhou.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.moonzhou.transaction.entity.Account;

/**
 * @author moon zhou
 * @version 1.0
 * @description
 * @date 2022/10/19 14:02
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
