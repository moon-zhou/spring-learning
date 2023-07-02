package org.moonzhou.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.jpa.entity.common.User;
import org.moonzhou.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void userSave() {
        /*
         * 使用同一个实例，报错：
         * org.hibernate.PersistentObjectException: detached entity passed to persist
         */
        User user1 = new User("moon", 18, "1@1.com", "nj");
        User user2 = new User("moon", 18, "1@1.com", "nj");

        /*
         * mysql:
         * Hibernate: select next_val as id_val from hibernate_sequence for update
         * Hibernate: update hibernate_sequence set next_val= ? where next_val=?
         * Hibernate: insert into t_user (age, email, name, id) values (?, ?, ?, ?)
         *
         * pg:
         * Hibernate: select nextval ('hibernate_sequence')
         * Hibernate: insert into t_user (age, email, name, id) values (?, ?, ?, ?)
         */
        userService.add1stUser(user1);
        userService.add2ndUser(user2);
    }

    @Test
    public void userUpdate() {
        /*
         * 与保存一样，使用同一个实例，报错：
         * org.hibernate.PersistentObjectException: detached entity passed to persist
         */
        User user1 = new User(1L, "moon", 19, "1@1.com", "nj");
        User user2 = new User(1L, "moon", 19, "1@1.com", "nj");

        userService.update1stUser(user1);
        userService.update2ndUser(user2);
    }
}