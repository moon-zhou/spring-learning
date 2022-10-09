package org.moon.service;

import org.moon.dto.UserDto;
import org.moon.param.UserParam;

/**
 * @author moon zhou
 * @version 1.0
 * @description: user service
 * @date 2022/10/9 17:28
 */
public interface UserService {
    UserDto getUser(UserParam userParam);
}
