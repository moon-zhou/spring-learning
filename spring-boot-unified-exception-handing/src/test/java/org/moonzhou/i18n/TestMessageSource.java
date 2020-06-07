package org.moonzhou.i18n;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class TestMessageSource {
    private static final Logger logger = LoggerFactory.getLogger(TestMessageSource.class);

    @Autowired
    private MessageSource messageSource;

    @Test
    public void testGetMessage() {
        logger.info(messageSource.getMessage("message.key.test", null, "", null));
    }
}