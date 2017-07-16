package com.jy.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MyResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    private static final Log logger = LogFactory.getLog(ResourceHttpRequestHandler.class);

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For very general mappings (e.g. "/") we need to check 404 first
        Resource resource = getResource(request);
        if (resource == null) {
            logger.info("No matching resource found - returning 500");
            throw new ModelAndViewDefiningException(new ModelAndView("500", new HashMap<>()));
        }
        super.handleRequest(request, response);
    }

    private static String getRequestUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        return uri;
    }
}
