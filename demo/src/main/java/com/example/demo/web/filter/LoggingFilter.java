package com.example.demo.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("loggingFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("loggingFilter.doFilter() START: REQUEST [{}][{}]", uuid, requestURI);
            // 다음 필터 호출하고 만약 더 이상 호출할 필터가 없으면 그 떄 dispatcherServlet 호출
            // 즉 필터 구현 시 해당 코드 사용않으면 서비스가 먹통 됨
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("loggingFilter.doFilter() END: RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("loggingFilter.destroy()");
    }
}
