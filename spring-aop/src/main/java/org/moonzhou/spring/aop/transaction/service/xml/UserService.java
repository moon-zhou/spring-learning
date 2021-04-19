package org.moonzhou.spring.aop.transaction.service.xml;

import org.moonzhou.spring.aop.transaction.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xmlUserService")
public class UserService {
    @Autowired
    UserDao userDao;

    public void updateMoney() {
        userDao.addMoney("zhangsan", 200);
        int i = 1 / 0;
        userDao.minMoney("lisi", 200);
    }
}