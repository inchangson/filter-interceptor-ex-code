package com.example.demo.web.interceptor;

import com.example.demo.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        log.info("LoginInterceptor.preHandle() START: [{}]", requestURI);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

            log.info("LoginInterceptor.preHandle(): unauthenticated access [{}]", requestURI);
            // 로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
        }

        log.info("LoginInterceptor.preHandle() END: [{}]", requestURI);
        return true;
    }
}
