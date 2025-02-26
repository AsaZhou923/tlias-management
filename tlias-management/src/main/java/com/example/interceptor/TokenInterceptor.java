package com.example.interceptor;

import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*//1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }*/

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否为空
        if(token == null || token.isEmpty()){
            log.info("token为空，拦截,401");
            response.setStatus(401);
            return false;
        }

        //5.解析token
        try{
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token解析失败，拦截,401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("token解析成功，放行");
        return true;
    }
}
