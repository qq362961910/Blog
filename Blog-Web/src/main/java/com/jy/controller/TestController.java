package com.jy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/test")
@RestController
public class TestController {

    private final Logger logger = LogManager.getLogger(TestController.class);
//  private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping("/json")
    public Object testJson() {

        logger.trace("this is a trace message");
        logger.debug("this is a debug message");
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");

        return new HashMap<String, Object>() {{
            put("success", true);
        }};

    }
}
