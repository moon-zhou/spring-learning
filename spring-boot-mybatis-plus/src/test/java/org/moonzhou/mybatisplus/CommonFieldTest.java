package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.AccountMapper;
import org.moonzhou.mybatisplus.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author moon zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonFieldTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void test() {

        Account account = new Account();
        account.setUserNo("moon-001");
        account.setName("moon");
        account.setAge(18);
        account.setEmail("1@163.com");
        account.setDept("hr");

        // 插入数据
        int insertResult = accountMapper.insert(account);
        Assert.assertEquals(1, insertResult);

        // 查询
        Account moon = accountMapper.selectOne(new LambdaQueryWrapper<Account>().eq(Account::getName, "moon"));
        Assert.assertNotNull(moon);

        // 更新
        moon.setAge(19);
        int updateResult = accountMapper.updateById(moon);
        Assert.assertEquals(1, updateResult);

        // 逻辑删
        int deleteResult = accountMapper.deleteById(moon);
        Assert.assertEquals(1, deleteResult);

        // 再查询，无法查到
        Account moonDeleted = accountMapper.selectOne(new LambdaQueryWrapper<Account>().eq(Account::getName, "moon"));
        Assert.assertNull(moonDeleted);
    }


}