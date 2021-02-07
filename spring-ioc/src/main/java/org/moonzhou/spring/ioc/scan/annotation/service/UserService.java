package org.moonzhou.spring.ioc.scan.annotation.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<String> getAllUser() {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add("moonzhou:" + i);
        }
        return users;
    }
}