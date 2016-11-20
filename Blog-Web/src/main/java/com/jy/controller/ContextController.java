package com.jy.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ContextController {
    /**
     * 404
     * */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView noHandlerFound(NoHandlerFoundException e) {
        return new ModelAndView("404");
    }

    /**
     * 500
     * */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        return new ModelAndView("500");
    }
}
