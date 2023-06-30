package org.moonzhou.jpa.service;

import org.moonzhou.jpa.entity.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/6/29 15:36
 */
@Service
public class UserService {
    @Autowired
    @Qualifier("primaryEntityManagerFactory")
    private EntityManagerFactory primaryEntityManagerFactory;

    @Autowired
    @Qualifier("secondEntityManagerFactory")
    private EntityManagerFactory secondEntityManagerFactory;

    @Transactional(transactionManager = "primaryTransactionManager")
    public void add1stUser(User user) {
        EntityManager entityManager = primaryEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public void update1stUser(User user) {
        EntityManager entityManager = primaryEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public void add2ndUser(User user) {
        EntityManager entityManager = secondEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public void update2ndUser(User user) {
        EntityManager entityManager = secondEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
