package org.moonzhou.springcache.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.springcache.dto.BizDto;
import org.moonzhou.springcache.param.BizParam;
import org.moonzhou.springcache.service.BizService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 */
@Slf4j
@RequestMapping("/spring-cache")
@RestController
@AllArgsConstructor
public class BizController {

    private final BizService bizService;

    /**
     * http://localhost:8080/spring-cache/getById
     * http://localhost:8080/spring-cache/getById/111
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getById/{id}")
    public BizDto getById(@PathVariable Long id) {
        return bizService.getById(id);
    }


    /**
     * http://localhost:8080/spring-cache/updateByID?id=111&name=moon&age=19
     *
     * @param bizParam
     */
    @ResponseBody
    @RequestMapping(value = "/updateByID")
    public BizDto updateByID(BizParam bizParam) {
        return bizService.updateById(bizParam);
    }

    /**
     * http://localhost:8080/spring-cache/save?id=111&name=moon&age=20
     *
     * @param bizParam
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public BizDto save(BizParam bizParam) {
        return bizService.save(bizParam);
    }

    /**
     * http://localhost:8080/spring-cache/deleteById
     * http://localhost:8080/spring-cache/deleteById/111
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        bizService.deleteById(id);
    }
}