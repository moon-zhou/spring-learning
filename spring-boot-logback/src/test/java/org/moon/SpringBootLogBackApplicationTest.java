package org.moon;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootLogBackApplicationTest {

    @Test
    public void test() {
        // Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        // LoggerContext loggerContext = rootLogger.getLoggerContext();
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext) {

            LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
            Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
            // we are not interested in auto-configuration
            loggerContext.reset();

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(loggerContext);
            encoder.setPattern("%-5level [%thread]: %message%n");
            encoder.start();

            ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
            appender.setContext(loggerContext);
            appender.setEncoder(encoder);
            appender.start();

            rootLogger.addAppender(appender);

            rootLogger.debug("Message 1");
            rootLogger.warn("Message 2");
        }
    }
}