package org.moon.util;

import lombok.extern.slf4j.Slf4j;
import org.moon.base.BaseParam;
import org.moon.base.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log factory
 * @date 2023/1/13 14:17
 */
@Slf4j
public class RunTimeLogUtil {
    /**
     * param or result ignore, do not log, like sensitive data or big data
     * format: className_methodName
     */
    private static final List<String> REQUEST_PARAM_IGNORE = new ArrayList<>();
    private static final List<String> REQUEST_RESULT_IGNORE = new ArrayList<>();

    static {
        // TODO
        REQUEST_PARAM_IGNORE.add("");


        REQUEST_RESULT_IGNORE.add("");
    }

    private RunTimeLogUtil() {
    }

    /**
     * method start log
     *
     * @param className
     * @param methodName
     */
    public static void logStart(String className, String methodName) {
        String methodInfo = className + "_" + methodName;

        if (className.contains("Controller")) {
            log.info("=================================== {}: start", methodInfo);
        } else {
            log.info("*********************************** {}: start", methodInfo);
        }
    }

    /**
     * method end log
     *
     * @param className
     * @param methodName
     * @param time
     */
    public static void logEnd(String className, String methodName, long time) {
        String methodInfo = className + "_" + methodName;

        if (className.contains("Controller")) {
            log.info("=================================== {} end: {}.", methodInfo, time);
        } else {
            log.info("***********************************  {} end: {}.", methodInfo, time);
        }
    }

    /**
     * log request param
     * @param className
     * @param methodName
     * @param args
     */
    public static void logParam(String className, String methodName, Object[] args) {
        String methodInfo = className + "_" + methodName;

        // ignore method

        for (Object arg : args) {
            // 入参是否为 user
            if (arg instanceof BaseParam) {
                log.info("{} param -------> {}", methodInfo, JsonUtils.toJson(arg));
            } else if (arg instanceof Map) {
                log.info("{} param -------> {}", methodInfo, JsonUtils.toJson(arg));
            } else {
                log.info("{} param -------> {}", methodInfo, arg);
            }
        }
    }

    /**
     * log request result
     * @param className
     * @param methodName
     * @param result
     */
    public static void logResult(String className, String methodName, Object result) {
        String methodInfo = className + "_" + methodName;

        // ignore method

        if (result instanceof Result) {
            log.info("{} result -------> {}", methodInfo, JsonUtils.toJson(result));
        } else if (result instanceof Map) {
            log.info("{} result -------> {}", methodInfo, JsonUtils.toJson(result));
        } else {
            log.info("{} result -------> {}", methodInfo, result);
        }
    }

    public static void audit(String className, String methodName, boolean operationResult) {
        String methodInfo = className + "_" + methodName;

        if (className.contains("Controller")) {
            // TODO audit log
        }
    }
}
