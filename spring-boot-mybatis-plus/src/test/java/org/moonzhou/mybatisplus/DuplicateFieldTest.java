package org.moonzhou.mybatisplus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.UserDuplicateFieldMapper;
import org.moonzhou.mybatisplus.model.entity.UserDuplicateField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author moon zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DuplicateFieldTest {

    @Autowired
    private UserDuplicateFieldMapper userDuplicateFieldMapper;

    @Test
    public void test() {
        List<UserDuplicateField> users = userDuplicateFieldMapper.selectList("J");
        Assert.assertNotNull(users);
        System.out.println(users);
    }

}
