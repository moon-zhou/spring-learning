package org.moonzhou.pgconnection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.moonzhou.pgconnection.dao.UserMapper;
import org.moonzhou.pgconnection.model.User;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author moon zhou
 * @email ayimin1989@163.com
 * @date 2022/3/28 13:21
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {
}
