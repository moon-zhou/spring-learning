package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 测试参数映射<br>
 * TODO: https://juejin.im/post/5cbf11bd6fb9a0320c5ab128
 *
 * @author moon-zhou
 * @date 2020/6/12 17:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@RestController
@RequestMapping("/testParam")
public class TestController {

    /**
     * http://localhost/testParam/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost/testParam/testPathParam/moonzhou
     *
     * @param userName
     * @return
     */
    @RequestMapping("/testPathParam/{userName}")
    public TestDto testPathParam(@PathVariable(name = "userName") String userName) {

        TestDto testDto = new TestDto();
        testDto.setUserName(userName);
        testDto.setHandSome(true);

        return testDto;
    }

    /**
     * http://localhost/testParam/testPathMultiParam/moonzhou/18
     *
     * 暂时无法映射boolean和data
     *
     * @param userName
     * @return
     */
    @RequestMapping("/testPathMultiParam/{userName}/{age}/{id}")
    public TestDto testPathMultiParam(@PathVariable(name = "userName") String userName, @PathVariable(name = "age") int age,
                                      @PathVariable(name = "id") long id) {

        TestDto testDto = new TestDto();
        testDto.setUserName(userName);
        testDto.setAge(age);
        testDto.setId(id);
//        testDto.setBirthday(birthday);
        testDto.setHandSome(true);

        return testDto;
    }
}
