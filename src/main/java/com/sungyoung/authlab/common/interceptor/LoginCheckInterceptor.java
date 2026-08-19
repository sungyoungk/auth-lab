package com.sungyoung.authlab.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        HttpSession session = request.getSession(false); // false: 세션이 없으면 새로 만들지 않음

        if(session == null || session.getAttribute("memberId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
            return false;
        }

        return true;
    }
}
