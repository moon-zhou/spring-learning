package org.moonzhou.aspectj.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.moonzhou.aspectj.annotation.Text;
import org.moonzhou.aspectj.base.Result;
import org.moonzhou.aspectj.constant.DictEnum;
import org.moonzhou.aspectj.dto.ProcessDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * controller 返回枚举值时，如果没有定义对应的文案字段，可以在字段上添加相关注解，由aspect自动添加上展示的字段
 *
 * @author moon zhou
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:20
 **/
@Aspect
@Component
@Slf4j
public class TextAspect {

    private final ObjectMapper mapper;

    public TextAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("@within(org.springframework.stereotype.Controller) " +
            "|| @within(org.springframework.web.bind.annotation.RestController)")
    public void addTextPoint() {
        //Pointcut
    }

//    @Pointcut("execution(* org.moonzhou.aspectj.controller.TestController.findProcessById())")
//    public void aroundPointCut() {
//        //Pointcut
//    }
//
//    @Around(value = "aroundPointCut()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
//
//        Object result = null;
//        try {
//            result = proceedingJoinPoint.proceed();
//
//            if (result instanceof ProcessDto) {
//                ObjectNode jsonNodes = addTest(result);
//
//                // not work
//                // java.lang.ClassCastException: com.fasterxml.jackson.databind.node.ObjectNode cannot be cast to org.moonzhou.aspectj.dto.ProcessDto
//                result = jsonNodes;
//            }
//        } catch (Throwable e) {
//            log.error("monitor error: ", e);
//        }
//
//        log.info("around------------------");
//
//        return result;
//    }


    @AfterReturning(value = "addTextPoint()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        if (result instanceof Result && Objects.nonNull(((Result<?>) result).getData())) {
            Result res = (Result) result;
            if (res.getData() instanceof ProcessDto) {
                ObjectNode jsonNodes = addTest(res.getData());

                if (jsonNodes != null) {
                    try {
                        Field dataField = res.getClass().getDeclaredField("data");
                        dataField.setAccessible(true);
                        dataField.set(res, jsonNodes);
                    } catch (NoSuchFieldException e) {
                        log.error("set data by reflection, no field: ", e);
                    } catch (IllegalAccessException e) {
                        log.error("set data by reflection, set error: ", e);
                    }
                }
            } else {
                log.warn("result return type is Result, but data is not ProcessDto!!!");
            }
        } else if (result instanceof ProcessDto) {
            ObjectNode jsonNodes = addTest(result);

            // not work
            result = jsonNodes;
        } else if (result instanceof String) {
            // not work
            result = result + "---";
        } else {
            log.warn("return type is not Result or ProcessDto!!!");
        }

    }

    private ObjectNode addTest(Object record) {
        JsonNode node = mapper.valueToTree(record);
        ObjectNode item = mapper.createObjectNode();

        //not object
        if (!(node instanceof ObjectNode)) {
            return null;
        }
        item.setAll((ObjectNode) node);

        //get field list
        Field[] allFields = getAllFields(record);

        Map<String, List<Field>> fieldMap = Arrays.stream(allFields)
                .filter(f -> f.getAnnotation(Text.class) != null)
                .collect(Collectors.groupingBy(f -> f.getAnnotation(Text.class).enumCode()));

        // 注解上配置的值，如果是字典持久化的方案，需要用此值去数据源查询，此处固定配置未枚举，用不到
        // Set<String> codes = fieldMap.keySet();

        fieldMap.forEach((id, fields) -> fields.forEach(field -> {
            String value = item.get(field.getName()).asText();
            String textValue = translateDictValue(id, value);

            // TODO 核心添加的字段值
            item.put(field.getName() + "Text", textValue);
        }));
        return item;
    }

    private Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    private String translateDictValue(String id, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        return DictEnum.getTextByIdCode(id, value);
    }
}
