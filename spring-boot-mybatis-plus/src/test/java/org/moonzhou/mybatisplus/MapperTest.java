package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.UserInfoMapper;
import org.moonzhou.mybatisplus.dao.UserMapper;
import org.moonzhou.mybatisplus.model.entity.User;
import org.moonzhou.mybatisplus.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Before
    public void initData() {
        // 清空数据
        userMapper.delete(Wrappers.<User>lambdaQuery());

        // 初始化数据
        User user1 = new User(1L, "Jone", 18, "test1@baomidou.com");
        User user2 = new User(2L, "Jack", 20, "test2@baomidou.com");
        User user3 = new User(3L, "Tom", 28, "test3@baomidou.com");
        User user4 = new User(4L, "Sandy", 21, "test4@baomidou.com");
        User user5 = new User(5L, "Billie", 24, "test5@baomidou.com");

        userMapper.insert(user1);
        userMapper.insert(user2);
        userMapper.insert(user3);
        userMapper.insert(user4);
        userMapper.insert(user5);
    }

    @Test
    public void testSelectUser() {
        System.out.println(("----- testSelectUser method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * <code>selectMaps</code>:只查部分列
     * <code>selectObjs</code>:只查第一列
     * <code>selectList</code>:查所有列
     */
    @Test
    public void testSelectUserByCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 以J开头的，J%
        wrapper.select("id","name","email").likeRight("name","J");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

        // 只会返回第一个字段（第一列）的值，其他字段会被舍弃。此处值返回了id
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

        // 查询id为3的
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getId, 3);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectUserCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "J");

        Long count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(3L, "Tommy", 30, "test333@123.com");
        userMapper.updateById(user);

        userMapper.update(null, Wrappers.<User>lambdaUpdate().set(User::getEmail, "test555@123.com").eq(User::getId, 5L));

        userMapper.update(
                new User().setEmail("miemie@baomidou.com"),
                new QueryWrapper<User>()
                        .lambda().eq(User::getId, 2));
    }

    /**
     * count(*)
     */
    @Test
    public void testSelectUserInfo() {
        System.out.println(("----- testSelectUserInfo method test ------"));
        List<UserInfo> userInfoList = userInfoMapper.selectList(null);
        Assertions.assertEquals(5, userInfoList.size());
        userInfoList.forEach(System.out::println);
    }

    /**
     * 进行数据统计
     */
    @Test
    public void testSelectUserInfoFunction() {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();

        // 按照直属上级进行分组，查询每组的平均年龄，最大年龄，最小年龄
        // select avg(age) avg_age ,min(age) min_age, max(age) max_age from user group by manager_id having sum(age) < 500;

        wrapper.select("dept", "avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("dept").having("sum(age) < {0}", 500);
        List<Map<String, Object>> maps = userInfoMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

}