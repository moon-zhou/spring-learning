package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.AccountMapper;
import org.moonzhou.mybatisplus.dao.AccountPhysicalMapper;
import org.moonzhou.mybatisplus.model.entity.Account;
import org.moonzhou.mybatisplus.model.entity.AccountPhysical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author moon zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonFieldTest {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountPhysicalMapper accountPhysicalMapper;

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

    /**
     * test physical delete
     * insert - select - update - delete - none
     */
    @Test
    public void testPhysical() {
        AccountPhysical accountPhysical = new AccountPhysical();
        accountPhysical.setUserNo("moon-010");
        accountPhysical.setName("moon010");
        accountPhysical.setAge(25);
        accountPhysical.setEmail("ayimin1989@163.com");
        accountPhysical.setDept("IT");

        // insert db
        int insertResult = accountPhysicalMapper.insert(accountPhysical);
        Assert.assertEquals(1, insertResult);

        // select list form db
        List<AccountPhysical> accountPhysicalList = accountPhysicalMapper.selectList(new LambdaQueryWrapper<AccountPhysical>().like(Objects.nonNull(accountPhysical),
                AccountPhysical::getName, accountPhysical.getName()));
        Assert.assertNotNull(accountPhysicalList);

        // select one
        AccountPhysical selectOneResult = accountPhysicalMapper.selectOne(new LambdaQueryWrapper<AccountPhysical>().eq(AccountPhysical::getName, accountPhysical.getName()));
        Assert.assertNotNull(selectOneResult);

        // update
        selectOneResult.setAge(26);
        int updateResult = accountPhysicalMapper.updateById(selectOneResult);
        Assert.assertEquals(1, updateResult);

        // delete
        int deleteResult = accountPhysicalMapper.deleteById(selectOneResult.getId());
        Assert.assertEquals(1, deleteResult);
    }

    /**
     * test findAll & deleteAll
     *
     * insert batch - findAll - deleteAll - none
     */
    @Test
    public void testCommonMethod() {
        AccountPhysical accountPhysical1 = new AccountPhysical();
        accountPhysical1.setUserNo("moon-010");
        accountPhysical1.setName("moon010");
        accountPhysical1.setAge(25);
        accountPhysical1.setEmail("ayimin1989@163.com");
        accountPhysical1.setDept("IT");

        AccountPhysical accountPhysical2 = new AccountPhysical();
        accountPhysical2.setUserNo("moon-011");
        accountPhysical2.setName("moon011");
        accountPhysical2.setAge(26);
        accountPhysical2.setEmail("ayimin1989@163.com");
        accountPhysical2.setDept("IT");

        // batch save
        int saveBatchResult = accountPhysicalMapper.insertBatchSomeColumn(Arrays.asList(accountPhysical1, accountPhysical2));
        Assert.assertEquals(2, saveBatchResult);

        // find all
        List<AccountPhysical> all = accountPhysicalMapper.findAll();
        Assert.assertEquals(2, all.size());

        // delete all
        int deleteAllResult = accountPhysicalMapper.deleteAll();
        Assert.assertEquals(2, deleteAllResult);
    }

}