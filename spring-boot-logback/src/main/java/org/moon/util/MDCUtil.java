package org.moon.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author moon zhou
 */
@Slf4j
public class MDCUtil {

    public MDCUtil() {
        throw new IllegalStateException("can not init, use directly");
    }

    private static final String TRACE_ID = "traceId";

    private static final String USER_NO = "userNo";

    public static String getTraceId() {
        String traceId = (String) MDC.get(TRACE_ID);
        return traceId == null ? "" : traceId;
    }

    public static void setTraceId() {
        setTraceId(generateTraceId());
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }

    public static void setUserInfo() {
        try {
            // get user no from sso context by sso client
            // here is just mock
            MDC.put(USER_NO, "001");
        } catch (Exception e) {
            // no user info
            log.warn("can not get user info from sso context, confirm the request url is restricted, not in white list.");
        }
    }

    public static void removeUserInfo() {
        MDC.remove(USER_NO);
    }

    public static void clear() {
        MDC.clear();
    }

    private static String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}