package com.example.demo.web.filter;

import com.example.demo.domain.item.ItemRepository;
import com.example.demo.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter
@Component
@Order(2)
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private final ItemRepository itemRepository;

    private static final String[] whitelist = {"/", "/space", "/space/members/add", "/space/login", "/space/logout", "/space/info", "/css/*"};

    private void testUsingSpringBean() {
        // spring bean 활용 가능 테스트용 코드
        int totalItemCount = itemRepository.findAll().size();
        log.info("LoginFilter.doFilter() totalItemCount: {}", totalItemCount);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("LoginFilter.doFilter() START: REQUEST_URI [{}]", requestURI);

            testUsingSpringBean();

            if (isLoginCheckPath(requestURI)) {
                log.info("LoginFilter.doFilter(): 인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("LoginFilter.doFilter(): 미인증 사용자 요청 {}", requestURI);
                    // 로그인으로 리다이렉트
                    // httpResponse.sendRedirect("/login"); 로
                    // 로그인페이지 가도 되나,
                    // 상품 조회 중 미인증으로 인한 로그인 페이지 이동 시,
                    // 로그인 이후에 다시 원래 페이지로 이동키 위해 ?redirectURL= 추가
                    httpResponse.sendRedirect("/space/login?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;// 예외 로깅 가능하지만 톰캣까지 예외를 보내주어야 함
        } finally {
            log.info("LoginFilter.doFilter() START: REQUEST_URI [{}]", requestURI);
        }
    }


    /**
     * 화이트 리스트 체크
     *
     * @param requestURI 요청 uri
     * @return 요청 uri 화이트리스트 해당 여부
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
