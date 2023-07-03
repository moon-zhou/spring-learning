package org.moonzhou.introspector.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.introspector.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/5/27 20:15
 **/
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * http://localhost:8080/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot pg connection test!!!";
    }

    /**
     * http://localhost:8080/test/get-user
     *
     * @return
     */
    @RequestMapping("/get-user")
    public UserDto getUser() {

        UserDto userDto = new UserDto("moon", 18);

        return userDto;
    }

    /**
     * http://localhost:8080/test/get-user-change
     *
     * @return
     */
    @SneakyThrows
    @RequestMapping("/get-user-change")
    public UserDto getUserChange() {

        UserDto userDto = new UserDto("moon", 18);

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", UserDto.class);

        Method readMethod = propertyDescriptor.getReadMethod();
        String name = (String)readMethod.invoke(userDto);
        log.info("method read name: {}, invoke value: {}.", readMethod.getName(), name);

        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(userDto, "zhou");
        log.info("method write name: {}, new result: {}", writeMethod.getName(), userDto.getName());

        return userDto;
    }


}
