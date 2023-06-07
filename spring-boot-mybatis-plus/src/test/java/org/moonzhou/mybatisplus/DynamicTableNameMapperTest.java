package org.moonzhou.mybatisplus;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.UserCodeMapper;
import org.moonzhou.mybatisplus.model.entity.UserCodeDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author moon zhou
 * @description test dynamic table name operation by mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicTableNameMapperTest {

    private static final String TABLE_NAME = "user_code";

    @Autowired
    private UserCodeMapper userCodeMapper;

    @Test
    public void testSelectUser() {
        UserCodeDiff userCodeDiff = userCodeMapper.selectUserById(TABLE_NAME, "1");
        Assertions.assertNotNull(userCodeDiff);
        Assertions.assertEquals(1, userCodeDiff.getId());
    }

    @Test
    public void testSelectUserList() {
        Set<Long> ids = new HashSet<>(Arrays.asList(1L, 2L, 3L));
        List<UserCodeDiff> userCodeDiffs = userCodeMapper.selectUserListByIds(TABLE_NAME, ids);
        Assertions.assertNotNull(userCodeDiffs);
        Assertions.assertEquals(3, userCodeDiffs.size());
    }

    @Test
    public void testSaveBatch() {
        UserCodeDiff userCodeDiff1 = new UserCodeDiff(111L, "moon111", 18, "moon@moon.com");
        UserCodeDiff userCodeDiff2 = new UserCodeDiff(222L, "moon222", 18, "moon@moon.com");
        UserCodeDiff userCodeDiff3 = new UserCodeDiff(333L, "moon333", 18, "moon@moon.com");

        List<UserCodeDiff> userCodeDiffList = new ArrayList<>();
        userCodeDiffList.add(userCodeDiff1);
        userCodeDiffList.add(userCodeDiff2);
        userCodeDiffList.add(userCodeDiff3);

        Long insertNum = userCodeMapper.saveBatch(TABLE_NAME, userCodeDiffList);
        Assertions.assertEquals(3, insertNum);

        // clear test data
        Set<Long> ids = userCodeDiffList.stream().map(UserCodeDiff::getId).collect(Collectors.toSet());
        Long deleteNum = userCodeMapper.deleteBatch(TABLE_NAME, ids);
        Assertions.assertEquals(3, deleteNum);
    }

}