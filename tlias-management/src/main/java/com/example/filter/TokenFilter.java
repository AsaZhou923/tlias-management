package com.example.filter;

import com.aliyun.oss.model.LiveChannelListing;
import com.example.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求
        if (requestURI.contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否为空
        if (token == null || token.isEmpty()){
            log.info("token为空，拦截,401");
            response.setStatus(401);
            return;
        }

        //5.校验token
        try{
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token解析失败，拦截,401");
            response.setStatus(401);
            return;
        }

        //6.放行
        log.info("token解析成功，放行");
        filterChain.doFilter(request,response);
    }
}
