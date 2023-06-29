package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author moon zhou
 * @description test id generator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGeneratorTest {


    @Test
    public void testSelectUser() {
        IdentifierGenerator mpDefaultIdGenerator = new DefaultIdentifierGenerator();
        String id = mpDefaultIdGenerator.nextId(mpDefaultIdGenerator).toString();
        Assert.notNull(id);
        Assert.notNull(id, "id not null");
    }

}