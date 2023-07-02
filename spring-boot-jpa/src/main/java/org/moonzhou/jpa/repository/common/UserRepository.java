package org.moonzhou.jpa.repository.common;

import org.moonzhou.jpa.entity.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 方法名规则，自动映射sql，无需手写
     * where address=? and id<=?
     * @param address
     * @param id
     * @return
     */
    List<User> getUserByAddressEqualsAndIdLessThanEqual(String address, Long id);

    @Query(value = "select * from t_user where id=(select max(id) from t_user)", nativeQuery = true)
    User maxIdUser();
}
