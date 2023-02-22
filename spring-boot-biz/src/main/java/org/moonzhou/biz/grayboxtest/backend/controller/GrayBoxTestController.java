package org.moonzhou.biz.grayboxtest.backend.controller;

import lombok.RequiredArgsConstructor;
import org.moonzhou.biz.grayboxtest.backend.dto.Result;
import org.moonzhou.biz.grayboxtest.backend.service.GrayBoxTestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author moon zhou
 * @version 1.0
 * @description: gray box backend config: fixed user operation
 * @date 2023/02/23 15:55
 */
@Component
@RestController
@RequestMapping("/api/v1/grayboxtest")
@RequiredArgsConstructor
public class GrayBoxTestController {

    private final GrayBoxTestService grayBoxTestService;


    /**
     * query gray box test status
     * 0: off
     * else:on
     *
     * @return
     */
    @GetMapping("/status")
    public Result<String> queryGrayBoxTestStatus() {
        return Result.success(grayBoxTestService.queryStatus());
    }

    /**
     * set gray box test status
     * 0: off
     * 1: on
     *
     * @return
     */
    @PutMapping("/setStatus/{status}")
    public Result<Void> setGrayBoxTestStatus(@PathVariable String status) {
        grayBoxTestService.setStatus(status);
        return Result.success();
    }

    /**
     * del data: no value, query result is null
     * @return
     */
    @DeleteMapping("/delStatus")
    public Result<Boolean> delGrayBoxTestStatus() {
        return Result.success(grayBoxTestService.delStatus());
    }

    /**
     * query gray box test user list
     *
     * @return
     */
    @GetMapping("/userList")
    public Result<Set> queryGrayBoxTestUserList() {
        return Result.success(grayBoxTestService.queryUserList());
    }

    /**
     * set gray box test user
     *
     * @return
     */
    @PutMapping("/setUser/{userNo}")
    public Result<Long> setGrayBoxTestUser(@PathVariable String userNo) {
        return Result.success(grayBoxTestService.setUser(userNo));
    }

    /**
     * set gray box test user list
     * @param userNos
     * @return
     */
    @PostMapping("/setUsers")
    public Result<Long> setGrayBoxTestUser(@RequestBody String[] userNos) {
        return Result.success(grayBoxTestService.setUserList(userNos));
    }

    /**
     * remove users in test list
     * @param userNos
     * @return
     */
    @DeleteMapping("/removeUsers")
    public Result<Long> delGrayBoxTestUser(@RequestBody String[] userNos) {
        return Result.success(grayBoxTestService.removeUsers(userNos));
    }

    /**
     * delete all test user list, query result is null
     * @return
     */
    @DeleteMapping("/delUsers")
    public Result<Boolean> delGrayBoxTestUser() {
        return Result.success(grayBoxTestService.delUsers());
    }

    /**
     * delete user list and status, all data is null
     * @return
     */
    @GetMapping("/clear")
    public Result<Boolean> delCacheData() {
        return Result.success(grayBoxTestService.delCacheData());
    }
}
