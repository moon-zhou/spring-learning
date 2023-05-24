package org.moonzhou.pgconnection.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.moonzhou.pgconnection.model.User;

/**
 * @author moon zhou
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}