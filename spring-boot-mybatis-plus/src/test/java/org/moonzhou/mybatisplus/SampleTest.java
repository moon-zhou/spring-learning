package org.moonzhou.mybatisplus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moonzhou.mybatisplus.dao.UserInfoMapper;
import org.moonzhou.mybatisplus.dao.UserMapper;
import org.moonzhou.mybatisplus.model.po.User;
import org.moonzhou.mybatisplus.model.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelectUser() {
        System.out.println(("----- testSelectUser method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectUserInfo() {
        System.out.println(("----- testSelectUserInfo method test ------"));
        List<UserInfo> userInfoList = userInfoMapper.selectList(null);
        Assertions.assertEquals(5, userInfoList.size());
        userInfoList.forEach(System.out::println);
    }
}