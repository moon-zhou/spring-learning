/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SQLInjectionUser.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/11 15:43
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.dto;

import lombok.*;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SQLInjectionUser {

    private Integer id;

    /**
     * 部分参数构造函数
     */
    @NonNull
    private String name;

    private String gender;
}
