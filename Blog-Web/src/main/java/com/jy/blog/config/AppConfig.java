package com.jy.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.blog.controller.interceptor.RequiredLoginInterceptor;
import com.jy.blog.helper.QiNiuHelper;
import com.jy.blog.helper.SmsHelper;
import com.jy.blog.interceptor.ResourceInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(
    basePackages = "com.jy"
)
@PropertySource({"classpath:props/db.properties",
    "classpath:props/hibernate.properties",
    "classpath:props/qiniu.properties",
    "classpath:props/sms.properties"})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;
    @Autowired
    private RequiredLoginInterceptor requiredLoginInterceptor;
    @Autowired
    private ObjectMapper objectMapper;

    /* 数据模型package */
    private static String[] MODEL_PACKAGES = {
        "com.jy.blog.entity"
    };

    /**
     * 静态文件支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/**/*.html").addResourceLocations("/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourceInterceptor());
        registry.addInterceptor(requiredLoginInterceptor);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 为文件上传配置CommonsMultiPartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(2097152); //上传文件为20MB
        return resolver;
    }

    /**
     * 数据源
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("db.driver.class"));
        dataSource.setJdbcUrl(environment.getProperty("db.url"));
        dataSource.setUser(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setInitialPoolSize(environment.getProperty("db.initialPoolSize", Integer.class));
        dataSource.setMinPoolSize(environment.getProperty("db.minPoolSize", Integer.class));
        dataSource.setMaxPoolSize(environment.getProperty("db.maxPoolSize", Integer.class));
        dataSource.setMaxIdleTime(environment.getProperty("db.maxIdleTime", Integer.class));
        dataSource.setMaxConnectionAge(environment.getProperty("db.maxConnectionAge", Integer.class));
        dataSource.setIdleConnectionTestPeriod(environment.getProperty("db.idleConnectionTestPeriod", Integer.class));
        return dataSource;
    }

    /**
     * 数据连接会话
     */
    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(MODEL_PACKAGES);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Hibernate ORM 配置
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    /**
     * 事物管理器
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    /**
     * QiNiuHelper
     */
    @Bean
    public QiNiuHelper qiNiuHelper() {
        QiNiuHelper qiNiuHelper = new QiNiuHelper();
        qiNiuHelper.setAccessKey(environment.getProperty("qiniu.access.key"));
        qiNiuHelper.setSecretKey(environment.getProperty("qiniu.secret.key"));
        qiNiuHelper.setBaseUrl(environment.getProperty("qiniu.base.url"));
        qiNiuHelper.setImg404(environment.getProperty("qiniu.img.404"));
        qiNiuHelper.setArticleDefaultImg(environment.getProperty("qiniu.article.coverImg"));
        qiNiuHelper.setPublicImgBucket(environment.getProperty("qiniu.public.img.bucket"));
        qiNiuHelper.init();
        return qiNiuHelper;
    }

    @Bean
    public SmsHelper smsHelper() {
        SmsHelper smsHelper = new SmsHelper();
        smsHelper.setUsername(environment.getProperty("sms.username"));
        smsHelper.setPassword(environment.getProperty("sms.password"));
        smsHelper.setBaseUrl(environment.getProperty("sms.base_url"));
        return smsHelper;
    }

//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//
//        DefaultResourceLoader loader = new DefaultResourceLoader();
//        MyResourceHttpRequestHandler handler = new MyResourceHttpRequestHandler();
//        List<Resource> locations = new ArrayList<>();
//        locations.add(loader.getResource("/"));
//        handler.setLocations(locations);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("/**/*.html", handler);
//
//        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
//        simpleUrlHandlerMapping.setUrlMap(map);
//
//        return simpleUrlHandlerMapping;
//    }

//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
//
//        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//
//        //page and statusCode pare
//        //you also can use method setStatusCodes(properties)
//        resolver.addStatusCode("404", 404);
//
//        //set views for exception
//        Properties mapping = new Properties();
//        mapping.put("ua.package.CustomException" , "page");
//        resolver.setExceptionMappings(mapping);
//
//        return resolver;
//    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mm = new MappingJackson2HttpMessageConverter();
        mm.setObjectMapper(objectMapper);
        converters.add(mm);
    }


}
