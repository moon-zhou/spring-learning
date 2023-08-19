package org.moonzhou.springcache.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.springcache.dto.BizDto;
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
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getById/{id}")
    public BizDto getById(@PathVariable Long id) {
        return bizService.getById(id);
    }

}