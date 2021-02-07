package org.moonzhou.spring.ioc.scan.xml;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserXmlConfService {
    public List<String> getAllUser() {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add("hou:" + i);
        }
        return users;
    }
}