package org.moonzhou.introspector.dto;

import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/5/27 20:16
 */
public class UserDto {

    private String name;
    private int age;

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /***************Introspector field*******************/
    private void setId(String id){

    }
    private String getId() {
        return String.format("generateId: %s", "001");
    }

    private void setUpdateTime(LocalDateTime localDateTime){

    }

    private LocalDateTime getUpdateTime() {
        return LocalDateTime.now();
    }
    /***************Introspector field*******************/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
