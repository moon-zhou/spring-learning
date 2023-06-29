package org.moonzhou.mybatisplus.logging;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * based on slf4, implement customized mybatis plus sql log
 * dependence project logback config
 * @author moon zhou
 */
public class MySqlLogImpl implements Log {

    private final Logger log;

    public MySqlLogImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        this.log = logger;
    }
    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable throwable) {
        log.error(s, throwable);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        if (s.startsWith("==>") || s.startsWith("<==")) {
            log.info(s);
        } else {
            log.debug(s);
        }
    }

    @Override
    public void trace(String s) {
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }
}