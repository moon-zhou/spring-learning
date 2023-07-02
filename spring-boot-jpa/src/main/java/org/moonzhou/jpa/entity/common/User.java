package org.moonzhou.jpa.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 默认情况下，如果数据库表是使用标准的下划线命名，并且能对应上实体类的类名，我们就不需要特别去手动匹配。比如有张 user_info 表，那么会自动匹配UserInfo这个实体类
 *
 * @author moon zhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // mysql生成sequence表，pg生成sequence
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增，mysql可以，pg不行，没有自增属性
    private Long id;

    private String name;
    private Integer age;
    private String email;

    private String address;

    public User(String name, Integer age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }
}