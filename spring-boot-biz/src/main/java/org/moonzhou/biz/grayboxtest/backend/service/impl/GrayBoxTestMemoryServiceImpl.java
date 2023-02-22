package org.moonzhou.biz.grayboxtest.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.moonzhou.biz.grayboxtest.backend.service.GrayBoxTestService;
import org.moonzhou.biz.grayboxtest.backend.util.DataCache;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author moon zhou
 * @version 1.0
 * @description: config data store in jvm
 * @date 2023/2/22 17:57
 */
@Service
public class GrayBoxTestMemoryServiceImpl implements GrayBoxTestService {
    public static final String STATUS_OFF = "0";

    @Override
    public boolean passGrayBoxTest(String userNo) {
        // off or not config, pass directly
        String status = DataCache.getGrayBoxTestStatus();
        if (null == status || StringUtils.equals(STATUS_OFF, status)) {
            return true;
        }

        return DataCache.containsUser(userNo);
    }

    @Override
    public String queryStatus() {
        return DataCache.getGrayBoxTestStatus();
    }

    @Override
    public void setStatus(String status) {
        DataCache.setGrayBoxTestStatus(status);
    }

    @Override
    public boolean delStatus() {
        return DataCache.delGrayBoxTestStatus();
    }

    @Override
    public Set queryUserList() {
        return DataCache.getGrayBoxTestUserList();
    }

    @Override
    public Long setUser(String userNo) {
        return setUserList(userNo);
    }

    @Override
    public Long setUserList(String... userNoList) {
        return DataCache.setGrayBoxTestUserList(userNoList);
    }

    @Override
    public Long removeUsers(String... userNoList) {
        return DataCache.removeGrayBoxTestUserList(userNoList);
    }

    @Override
    public Boolean delUsers() {
        return DataCache.delGrayBoxTestUserList();
    }

    @Override
    public Boolean delCacheData() {
        return DataCache.delCacheData();
    }
}
