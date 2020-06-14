package org.moonzhou.exception.version2;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/14 15:57
 * @since 1.0
 */
public class MapResultHandle {
    /**
     * 响应码，新标准
     */
    public static final String RESPONSE_CODE = "responseCode";
    /**
     * 响应信息 新标准
     */
    public static final String RESPONSE_MSG = "responseMsg";
    /**
     * 成功码
     */
    public static final String SUCCESS_CODE = "0000";

    /**
     * 错误码 老标准
     */
    public static final String ERROR_CODE = "errorCode";

    /**
     * 响应消息 老标准
     */
    public static final String MESSAGE = "message";

    /**
     * 成功 key 老标准
     */
    public static final String SUCCESS = "success";

    private MapResultHandle() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 是否成功,兼容新老ssf两套标准 <br>
     *
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isSuccess(Map<String, ?> result) {

        if (MapUtils.isNotEmpty(result)) {
            // 空值不考虑
            if (result.containsKey(RESPONSE_CODE)) {
                String responseCode = MapUtils.getString(result, RESPONSE_CODE);
                return SUCCESS_CODE.equals(responseCode);
            }
            if (result.containsKey(SUCCESS)) {
                return MapUtils.getBooleanValue(result, SUCCESS);
            }
        }
        return false;
    }

    /**
     *
     * 获取响应信息 <br>
     *
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getResponseMsg(Map<String, ?> result) {
        return getStringValue(result, RESPONSE_MSG);
    }

    /**
     *
     * 获取错误码 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getResponseCode(Map<String, ?> result) {
        return getStringValue(result, RESPONSE_CODE);
    }

    /**
     *
     * 获取老标准ssf返回值错误信息 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getMessage(Map<String, ?> result) {
        return getStringValue(result, MESSAGE);
    }

    /**
     *
     * 获取老标准ssf接口返回值的错误码 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getErrorCode(Map<String, ?> result) {
        return getStringValue(result, ERROR_CODE);
    }

    /**
     *
     * 获取字符串 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getStringValue(Map<String, ?> result, String key) {
        return MapUtils.getString(result, key);
    }

    /**
     *
     * 获取布尔值 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean getBoolValue(Map<String, ?> result, String key) {
        return MapUtils.getBooleanValue(result, key);
    }

    /**
     *
     * 获取整形值 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int getIntValue(Map<String, ?> result, String key) {
        return MapUtils.getIntValue(result, key);
    }

    /**
     *
     * 获取长整形值 <br>
     * 〈功能详细描述〉
     *
     * @param result
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static long getLongValue(Map<String, ?> result, String key) {
        return MapUtils.getLongValue(result, key);
    }

    /**
     * 失败报文格式 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String, Object> fail() {
        Map<String, Object> failResult = new HashMap<String, Object>();
        return fail(failResult);
    }

    /**
     * 失败报文格式 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String, Object> fail(Map<String, Object> result) {
        result.put(SUCCESS, false);
        return result;
    }

    /**
     * 初始化报文 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String, Object> initResult() {
        Map<String, Object> success = new HashMap<String, Object>();
        success.put(SUCCESS, true);
        return success;
    }
}
