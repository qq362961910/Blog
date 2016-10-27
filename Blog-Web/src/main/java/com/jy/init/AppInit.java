package com.jy.init;

import com.jy.config.AppConfig;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.Set;

public class AppInit implements WebApplicationInitializer,ServletContainerInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        /* Open Session In View */
        servletContext.addFilter("hibernateFilter", OpenSessionInViewFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        /* Dispatcher Servlet */
        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                "dispatcherServlet", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        /* Character Encoding Filter */
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
                "characterEncodingFilter", characterEncodingFilter);
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfiguration", "/WEB-INF/custom-name-log4j.xml");
    }
}
