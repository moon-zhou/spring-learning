package org.moonzhou.spring.aop.transaction.service.annotation;

import org.moonzhou.spring.aop.transaction.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("annotationUserService")
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public void updateMoney() {
        userDao.addMoney("zhangsan", 200);
        int i = 1 / 0;
        userDao.minMoney("lisi", 200);
    }
}