package org.moonzhou.biz.grayboxtest.backend.service;

import java.util.Set;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/23 14:55
 */
public interface GrayBoxTestService {

    /**
     * user in gray box test list, access can be authorized
     * true: have permissions
     * false: no permissions
     * @param userNo
     * @return
     */
    boolean passGrayBoxTest(String userNo);

    /**
     * query test switch: null/0-off  1-on
     * @return
     */
    String queryStatus();

    /**
     * set test switch: 0-off  1-on  other:error
     * @param status
     */
    void setStatus(String status);

    /**
     * delete test switch, no anything in cache(or other persistent software)
     * query result is null
     * @return
     */
    boolean delStatus();

    /**
     * query test user list, no duplicate data
     * @return
     */
    Set queryUserList();

    /**
     * set test user
     * @param userNo
     * @return
     */
    Long setUser(String userNo);
    Long setUserList(String...userNoList);

    /**
     * remove test user from set, type of data structure
     * query result is user list, extreme conditions is empty list, can not be null
     * @param userNoList
     * @return
     */
    Long removeUsers(String... userNoList);

    /**
     * delete test user list
     * query result is null
     * @return
     */
    Boolean delUsers();

    /**
     * delete status and user list
     * @return
     */
    Boolean delCacheData();
}
