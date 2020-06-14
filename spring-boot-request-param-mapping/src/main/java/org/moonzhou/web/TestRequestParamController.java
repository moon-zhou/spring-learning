package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TestDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试RequestParam注解方式获取参数
 * <p>
 * 1. 既可以使用链接传参，亦可以使用body（form-data）进行传参
 * 2. 不显示映射名称时，直接使用注解修饰的形参进行映射
 * 3. 不显示设置修饰的字段的必要性时，默认必传
 * 4. Data类型需要DateTimeFormat
 * 5. 正常映射boolean
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/14 9:28
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/testRequestParamParam")
public class TestRequestParamController {

    /**
     * http://localhost/testRequestParamParam/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost/testRequestParamParam/testSignalParam
     * <p>
     * 链接传参，body（form-data）传参都可以映射进来
     * header里传的参数无法映射进来
     *
     * @param userName
     * @return
     */
    @RequestMapping("testSignalParam")
    public TestDto testSignalParam(@RequestParam(name = "userName") String userName) {
        TestDto testDto = new TestDto();
        testDto.setUserName(userName);

        return testDto;
    }

    /**
     * http://localhost/testRequestParamParam/testSignalDiffMappingParam
     * <p>
     * 参数名称使用name，不能使用userName
     *
     * @param userName
     * @return
     */
    @RequestMapping("testSignalDiffMappingParam")
    public TestDto testSignalDiffMappingParam(@RequestParam(name = "name") String userName) {
        TestDto testDto = new TestDto();
        testDto.setUserName(userName);

        return testDto;
    }

    /**
     * http://localhost/testRequestParamParam/testSignalDefaultParam
     * <p>
     * 不显示定义<code>@RequestParam<code/>的映射名时，直接使用形参的名进行映射
     *
     * @param userName
     * @return
     */
    @RequestMapping("testSignalDefaultParam")
    public TestDto testSignalDefaultParam(@RequestParam String userName) {
        TestDto testDto = new TestDto();
        testDto.setUserName(userName);

        return testDto;
    }

    /**
     * http://localhost/testRequestParamParam/testMultiParam
     * <p>
     * Data类型需要DateTimeFormat
     *
     * @param userName
     * @param age
     * @param handSome
     * @param id
     * @param birthday
     * @return
     */
    @RequestMapping("testMultiParam")
    public TestDto testMultiParam(@RequestParam String userName, @RequestParam int age, @RequestParam boolean handSome,
                                  @RequestParam Long id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday) {
        TestDto testDto = new TestDto();
        testDto.setUserName(userName);
        testDto.setAge(age);
        testDto.setHandSome(handSome);
        testDto.setId(id);
        testDto.setBirthday(birthday);

        return testDto;
    }

    /**
     * http://localhost/testRequestParamParam/testDtoParam
     * <p>
     * 去掉<code>@RequestParam<code/>可以直接映射到dto，但是日期类型字段需要添加<code>@DateTimeFormat(pattern = "yyyy-MM-dd")</code>
     *
     * @param testDto
     * @return
     */
    @RequestMapping("testDtoParam")
    public TestDto testDtoParam(TestDto testDto) {

        return testDto;
    }
}
