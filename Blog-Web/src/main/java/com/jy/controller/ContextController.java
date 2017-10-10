package com.jy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ContextController {

    private final Logger logger = LogManager.getLogger(ContextController.class);

    /**
     * 404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView noHandlerFound(NoHandlerFoundException e) {
        logger.error("", e);
        return new ModelAndView("404");
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String NotFoundPage() {
        return "404";
    }

    /**
     * 500
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        logger.error("", e);
        return new ModelAndView("500");
    }
}
