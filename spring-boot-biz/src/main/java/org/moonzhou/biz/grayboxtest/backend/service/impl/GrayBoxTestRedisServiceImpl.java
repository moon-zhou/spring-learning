// package org.moonzhou.biz.grayboxtest.backend.service.impl;
//
// import org.apache.commons.lang3.StringUtils;
// import org.moonzhou.biz.grayboxtest.backend.service.GrayBoxTestService;
// import org.springframework.stereotype.Service;
//
// import java.util.Set;
//
// /**
//  * @author moon zhou
//  * @version 1.0
//  * @description: gray boxy test service
//  * @date 2023/2/23 14:55
//  */
// @Service
// public class GrayBoxTestRedisServiceImpl implements GrayBoxTestService {
//
//     /**
//      * gray box test status 0:off 1:on
//      */
//     public static final String GRAY_BOX_TEST_CACHE_SWITCH_KEY = "MOON_GRAY_BOX_STATUS";
//     public static final String GRAY_BOX_TEST_CACHE_SWITCH_VALUE_OFF = "0";
//
//     /**
//      * gray box test user list
//      */
//     public static final String GRAY_BOX_TEST_CACHE_USER_LIST_KEY = "MOON_GRAY_BOX_USER_LIST";
//
//     @Override
//     public boolean passGrayBoxTest(String userNo) {
//
//         // off, pass directly
//         String status = (String) RedisUtil.get(GRAY_BOX_TEST_CACHE_SWITCH_KEY);
//         if (null == status || StringUtils.equals(GRAY_BOX_TEST_CACHE_SWITCH_VALUE_OFF, status)) {
//             return true;
//         }
//
//         return RedisUtil.setContains(GRAY_BOX_TEST_CACHE_USER_LIST_KEY, uid);
//     }
//
//     @Override
//     public String queryGrayBoxTestStatus() {
//         return (String) RedisUtil.get(GRAY_BOX_TEST_CACHE_SWITCH_KEY);
//     }
//
//     @Override
//     public void setGrayBoxTestStatus(String status) {
//         RedisUtil.set(GRAY_BOX_TEST_CACHE_SWITCH_KEY, status);
//     }
//
//     @Override
//     public Set queryGrayBoxTestUserList() {
//         return RedisUtil.setMembers(GRAY_BOX_TEST_CACHE_USER_LIST_KEY);
//     }
//
//     @Override
//     public Long setGrayBoxTestUser(String uid) {
//         return setGrayBoxTestUsers(uid);
//     }
//
//     @Override
//     public Long setGrayBoxTestUsers(String... uids) {
//         return RedisUtil.setAdd(GRAY_BOX_TEST_CACHE_USER_LIST_KEY, uids);
//     }
//
//     @Override
//     public Long delGrayBoxTestUsers(String... uids) {
//         return RedisUtil.setRemove(GRAY_BOX_TEST_CACHE_USER_LIST_KEY, uids);
//     }
//
//     @Override
//     public Boolean delCacheData() {
//         return RedisUtil.del(GRAY_BOX_TEST_CACHE_SWITCH_KEY) && RedisUtil.del(GRAY_BOX_TEST_CACHE_USER_LIST_KEY);
//     }
// }
