package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TestDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 测试参数映射<br>
 * TODO: https://juejin.im/post/5cbf11bd6fb9a0320c5ab128
 *
 * 1. 参数必须是restful格式，作为请求的一部分
 * 2. 不显示映射名称时，直接使用注解修饰的形参进行映射
 * 3. 不显示设置修饰的字段的必要性时，默认必传
 * 4. Data类型需要DateTimeFormat
 * 5. 正常映射boolean
 *
 * @author moon-zhou
 * @date 2020/6/12 17:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@RestController
@RequestMapping("/testPathVariableParam")
public class TestPathVariableController {

    /**
     * http://localhost/testPathVariableParam/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost/testPathVariableParam/testPathParam/moonzhou
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
     * http://localhost/testPathVariableParam/testPathMultiParam/moonzhou/18/1
     *
     * @param userName
     * @return
     */
    @RequestMapping("/testPathMultiParam/{userName}/{age}/{id}/{birthday}/{handSome}")
    public TestDto testPathMultiParam(@PathVariable(name = "userName") String userName, @PathVariable(name = "age") int age,
                                      @PathVariable(name = "id") long id, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date birthday,
                                      @PathVariable boolean handSome) {

        TestDto testDto = new TestDto();
        testDto.setUserName(userName);
        testDto.setAge(age);
        testDto.setId(id);
        testDto.setBirthday(birthday);
        testDto.setHandSome(handSome);

        return testDto;
    }


}
