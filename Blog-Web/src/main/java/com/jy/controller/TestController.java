package com.jy.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/test")
@RestController
public class TestController {

    private final Logger logger = LogManager.getLogger(TestController.class);

    @RequestMapping("/json")
    public Object testJson() {

        if (logger.isDebugEnabled()) {
            logger.info("this is a debug message");
        }

        return new HashMap<String, Object>(){{
            put("success", true);
        }};

    }
}
