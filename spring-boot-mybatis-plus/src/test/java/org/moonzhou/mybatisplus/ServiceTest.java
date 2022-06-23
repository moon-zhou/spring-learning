package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.model.entity.User;
import org.moonzhou.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author moon zhou
 * @email ayimin1989@163.com
 * @date 2022/3/28 13:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Before
    public void initData() {
        // 清空数据
        userService.lambdaUpdate().remove();

        // 初始化数据
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "Jone", 18, "test1@baomidou.com");
        User user2 = new User(2L, "Jack", 20, "test2@baomidou.com");
        User user3 = new User(3L, "Tom", 28, "test3@baomidou.com");
        User user4 = new User(4L, "Sandy", 21, "test4@baomidou.com");
        User user5 = new User(5L, "Billie", 24, "test5@baomidou.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        userService.saveBatch(users);
    }

    @Test
    public void testGetOne() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getAge, 28);
        User none = userService.getOne(wrapper, false); // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        System.out.println("none: " + none);

        wrapper.clear();
        wrapper.gt(User::getAge, 21);
        User one = userService.getOne(wrapper, false); // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        System.out.println("one: " + one);

        try {
            User exception = userService.getOne(wrapper, true); // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChainSelect() {
        List<User> list = userService.lambdaQuery()
                .gt(User::getAge, 18)
                .like(User::getName, "a")
                .list();
        list.forEach(System.out::println);
    }

    @Test
    public void testChainUpdate() {
        boolean a = userService.lambdaUpdate()
                .gt(User::getAge, 18)
                .like(User::getName, "a")
                .set(User::getEmail, "w39@baomidou.com")
                .update();
        System.out.println(a);
    }

    @Test
    public void testChainRemove() {
        boolean a = userService.lambdaUpdate()
                .gt(User::getAge, 18)
                .like(User::getName, "a")
                .remove();
        System.out.println(a);
    }
}
