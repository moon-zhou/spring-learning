package org.moonzhou.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.moonzhou.mybatisplus.dao.UserMapper;
import org.moonzhou.mybatisplus.model.po.User;
import org.moonzhou.mybatisplus.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author moon zhou
 * @email ayimin1989@163.com
 * @date 2022/3/28 13:21
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
