package com.example.demo;

import com.example.demo.web.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean<Filter> logFilterBean = new FilterRegistrationBean<>();

        logFilterBean.setFilter(new LoggingFilter());
        logFilterBean.setOrder(1);
        logFilterBean.addUrlPatterns("/*");

        return logFilterBean;
    }
}
