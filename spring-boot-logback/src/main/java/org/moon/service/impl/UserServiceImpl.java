package org.moon.service.impl;

import org.moon.dto.UserDto;
import org.moon.param.UserParam;
import org.moon.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author moon zhou
 * @version 1.0
 * @description: user service
 * @date 2022/10/9 17:29
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUser(UserParam userParam) {
        UserDto userDto = new UserDto();
        userDto.setName(userParam.getName() + " good");
        userDto.setAge(userParam.getAge() + 1);

        return userDto;
    }
}
