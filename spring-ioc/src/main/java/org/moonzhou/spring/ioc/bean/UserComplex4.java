/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: UserComplex.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/5 10:57
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.bean;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: 复杂属性注入-map<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UserComplex4 {
    private Cat cat;

    private List<String> favorites;

    private List<Cat> cats;

    private Map<String,Object> map;

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "UserComplex4{" +
                "cat=" + cat +
                ", favorites=" + favorites +
                ", cats=" + cats +
                ", map=" + map +
                '}';
    }
}
