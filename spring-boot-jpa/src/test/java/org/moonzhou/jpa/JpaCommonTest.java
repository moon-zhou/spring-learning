package org.moonzhou.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.jpa.entity.common.User;
import org.moonzhou.jpa.repository.common.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaCommonTest {
    @Autowired
    UserRepository primaryUserRepository;

    @Autowired
    UserRepository secondUserRepository;

    @Test
    public void userSave() {
        User user = new User("moon", 18, "2@2.com", "南京");

        User save1 = primaryUserRepository.save(user);
        User save2 = secondUserRepository.save(user);

        Assert.assertNotNull(save1);
        Assert.assertNotNull(save2);
    }

    @Test
    public void testSearch() {
        List<User> userList1 = primaryUserRepository.getUserByAddressEqualsAndIdLessThanEqual("nj", 3L);
        List<User> userList2 = secondUserRepository.getUserByAddressEqualsAndIdLessThanEqual("nj", 3L);
        Assert.assertNotNull(userList1);
        Assert.assertEquals(0, userList1.size());
        Assert.assertNotNull(userList2);
        Assert.assertEquals(1, userList2.size());

        User user1 = primaryUserRepository.maxIdUser();
        User user2 = secondUserRepository.maxIdUser();
        Assert.assertNotNull(user1);
        Assert.assertNotNull(user2);
    }
}