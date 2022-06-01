package org.moonzhou.httputil.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * test param
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestParam {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
