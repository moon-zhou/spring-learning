package org.moonzhou.mybatisplus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.UserWageEncryptionAnnotationMapper;
import org.moonzhou.mybatisplus.dao.UserWageEncryptionResultMapMapper;
import org.moonzhou.mybatisplus.model.entity.UserWageEncryptionAnnotation;
import org.moonzhou.mybatisplus.model.entity.UserWageEncryptionResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbEncryptionTest {

    @Autowired
    private UserWageEncryptionAnnotationMapper userWageEncryptionAnnotationMapper;

    @Autowired
    private UserWageEncryptionResultMapMapper userWageEncryptionResultMapMapper;

    @Test
    public void testAnnotation() {

        // 英文
        UserWageEncryptionAnnotation user1 = new UserWageEncryptionAnnotation(1L, "1000", "good");
        int insert = userWageEncryptionAnnotationMapper.insert(user1);
        Assert.assertEquals(1, insert);

        // 中文
        UserWageEncryptionAnnotation user2 = new UserWageEncryptionAnnotation(2L, "一千", "厉害");
        Assert.assertEquals(1, userWageEncryptionAnnotationMapper.insert(user2));

        List<UserWageEncryptionAnnotation> userWageEncryptionAnnotations = userWageEncryptionAnnotationMapper.selectList(null);
        Assert.assertNotNull(userWageEncryptionAnnotations);
    }

    @Test
    public void testResultMap() {
        UserWageEncryptionResultMap user1 = new UserWageEncryptionResultMap(1L, "1000", "good");
        int insert = userWageEncryptionResultMapMapper.insertSelf(user1);
        Assert.assertEquals(1, insert);

        List<UserWageEncryptionResultMap> userWageEncryptionResultMaps = userWageEncryptionResultMapMapper.selectAll();
        Assert.assertNotNull(userWageEncryptionResultMaps);
    }
}
