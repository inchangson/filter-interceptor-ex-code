package com.example.demo;

import com.example.demo.web.filter.CsrfFilter;
import com.example.demo.web.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean<Filter> logRegBean = new FilterRegistrationBean<>();

        logRegBean.setFilter(new LoggingFilter());
        logRegBean.setOrder(1);
        logRegBean.addUrlPatterns("/*");

        return logRegBean;
    }

    @Bean
    public FilterRegistrationBean csrfFilter() {
        FilterRegistrationBean<Filter> csrfRegBean = new FilterRegistrationBean<>();

        csrfRegBean.setFilter(new CsrfFilter());
        csrfRegBean.setOrder(3);
        csrfRegBean.addUrlPatterns("/*");

        return csrfRegBean;
    }
}
