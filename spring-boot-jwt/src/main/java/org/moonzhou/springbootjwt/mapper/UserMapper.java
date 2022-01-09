package org.moonzhou.springbootjwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.moonzhou.springbootjwt.entity.User;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/5/5 20:58
 * @since 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户id查询用户信息
     * @param Id
     * @return
     */
    User findUserById(@Param("Id") String Id);
}
