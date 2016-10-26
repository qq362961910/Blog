package com.jy.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/test")
@RestController
public class TestController {

    private static final Logger logger = Logger.getLogger(TestController.class);

    @RequestMapping("/json")
    public Object testJson() {

        if (logger.isDebugEnabled()) {
            logger.debug("this is a debug message");
        }

        return new HashMap<String, Object>(){{
            put("success", true);
        }};

    }

    public TestController () {
        System.out.println("aaaaa");
    }

}
