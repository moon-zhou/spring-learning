package org.moonzhou.mybatisplus;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.UserCodeMapper;
import org.moonzhou.mybatisplus.model.entity.UserCodeDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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
    public void testCountUser() {
        // condition: entity
        UserCodeDiff userCodeDiff1 = new UserCodeDiff(null, "Tom", 28, null);
        UserCodeDiff userCodeDiff2 = new UserCodeDiff(null, "Tom", 29, null);

        int u1 = userCodeMapper.countUserByEntity(TABLE_NAME, userCodeDiff1);
        Assertions.assertEquals(1, u1);

        int u2 = userCodeMapper.countUserByEntity(TABLE_NAME, userCodeDiff2);
        Assertions.assertEquals(0, u2);


        // condition: map
        Map<String, String> param1 = new HashMap<>();
        param1.put("name", "Tom");
        param1.put("age", "28");

        Map<String, String> param2 = new HashMap<>();
        param2.put("name", "Tom");
        param2.put("age", "29");

        int uc1 = userCodeMapper.countUserByCondition(TABLE_NAME, param1);
        Assertions.assertEquals(1, uc1);

        int uc2 = userCodeMapper.countUserByCondition(TABLE_NAME, param2);
        Assertions.assertEquals(0, uc2);

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

    @SneakyThrows
    @Test
    public void testDynamicSave() {
        UserCodeDiff userCodeDiff = new UserCodeDiff(111L, "moon111", 18, "moon@moon.com");

        Map<String, Object> map = new HashMap<>();

        // 获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(UserCodeDiff.class);
        // 获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        // 对属性迭代
        for (PropertyDescriptor pd : pds) {
            if (!"class".equals(pd.getName())) {
                // 属性名称
                String propertyName = pd.getName();
                // 属性值,用getter方法获取
                Method m = pd.getReadMethod();
                // 用对象执行getter方法获得属性值
                Object properValue = m.invoke(userCodeDiff);
                // 把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }
        }

        Long result = userCodeMapper.dynamicSave(TABLE_NAME, map);
        Assertions.assertEquals(1L, result);

        // clear test data
        Long deleteNum = userCodeMapper.delete(TABLE_NAME, userCodeDiff.getId());
        Assertions.assertEquals(1, deleteNum);
    }

}