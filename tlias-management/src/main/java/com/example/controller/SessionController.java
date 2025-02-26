package com.example.controller;

import com.example.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {

    //设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","example"));//设置cookie/响应cookie
        return Result.success();
    }

    //获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username:" + cookie.getValue());//输出name为login_username的cookie的值
            }
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info("session1:{}",session.hashCode());

        session.setAttribute("loginUser","tom");//从session中存储数据
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session2(HttpSession session){
        log.info("session2:{}",session.hashCode());

        Object loginUser = session.getAttribute("loginUser");//从session中获取数据
        log.info("loginUser:{}",loginUser);
        return Result.success();
    }
}
