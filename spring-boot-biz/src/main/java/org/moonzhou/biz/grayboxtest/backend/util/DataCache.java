package org.moonzhou.biz.grayboxtest.backend.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author moon zhou
 * @version 1.0
 * @description: Stand-alone cache, in jvm. In distributed systems, distributed cache middleware instead. e.g.Redis. DB is also feasible.
 * @date 2023/2/22 16:38
 */
public class DataCache {

    private static String GRAY_BOX_TEST_STATUS;
    private static Set<String> GRAY_BOX_TEST_USER_LIST;

    private DataCache() {
    }

    public static String getGrayBoxTestStatus() {
        return GRAY_BOX_TEST_STATUS;
    }

    public static void setGrayBoxTestStatus(String grayBoxTestStatus) {
        GRAY_BOX_TEST_STATUS = grayBoxTestStatus;
    }

    public static Boolean delGrayBoxTestStatus() {
        GRAY_BOX_TEST_STATUS = null;
        return true;
    }

    public static Set<String> getGrayBoxTestUserList() {
        return GRAY_BOX_TEST_USER_LIST;
    }

    public static Long setGrayBoxTestUserList(String... userNoList) {
        if (null == GRAY_BOX_TEST_USER_LIST) {
            GRAY_BOX_TEST_USER_LIST = new HashSet<>();
        }

        boolean result = GRAY_BOX_TEST_USER_LIST.addAll(Arrays.asList(userNoList));
        return result ? Long.valueOf(userNoList.length) : 0L;
    }

    public static Long removeGrayBoxTestUserList(String... userNoList) {
        if (null == GRAY_BOX_TEST_USER_LIST) {
            return Long.valueOf(userNoList.length);
        }

        boolean result = GRAY_BOX_TEST_USER_LIST.removeAll(Arrays.asList(userNoList));
        return result ? Long.valueOf(userNoList.length) : 0L;
    }

    public static Boolean delGrayBoxTestUserList() {
        GRAY_BOX_TEST_USER_LIST = null;
        return true;
    }

    public static Boolean containsUser(String userNo) {
        if (null == GRAY_BOX_TEST_USER_LIST) {
            return false;
        }

        return GRAY_BOX_TEST_USER_LIST.contains(userNo);
    }

    public static Boolean delCacheData() {
        return delGrayBoxTestStatus() && delGrayBoxTestUserList();
    }
}
