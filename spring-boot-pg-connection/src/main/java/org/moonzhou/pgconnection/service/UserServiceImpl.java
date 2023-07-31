package org.moonzhou.pgconnection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.moonzhou.pgconnection.dao.UserMapper;
import org.moonzhou.pgconnection.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author moon zhou
 * @email ayimin1989@163.com
 * @date 2022/3/28 13:21
 **/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {

    public User customSave(User user) {
        user.setName("custom: " + user.getName());
        super.save(user);

        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
