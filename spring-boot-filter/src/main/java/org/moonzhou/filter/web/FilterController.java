package org.moonzhou.filter.web;

import org.moonzhou.filter.dto.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/7/05 09:45
 * @since 1.0
 */
@RestController
@RequestMapping("/filter")
public class FilterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    /**
     * http://localhost:8081/filter/componentfilter
     *
     * @return
     */
    @RequestMapping("/componentfilter")
    String componentFilter() {
        LOGGER.info("componentFilter controller.");

        return "hello component filter!!!";
    }

    /**
     * http://localhost:8081/filter/registrationfilter
     *
     * @return
     */
    @RequestMapping("/registrationfilter")
    String registrationFilter() {
        LOGGER.info("registrationFilter controller.");

        return "hello registration filter!!!";
    }

    /**
     * http://localhost:8081/filter/webfilter
     *
     * @return
     */
    @RequestMapping("/webfilter")
    String webFilter() {
        LOGGER.info("webFilter controller.");

        return "hello web filter!!!";
    }

}
