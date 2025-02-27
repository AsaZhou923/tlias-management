package com.example.aop;

import com.example.mapper.LoginLogMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpLoginLog;
import com.example.pojo.LoginInfo;
import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.util.StringUtils.split;

@Slf4j
@Aspect
@Component
public class LoginLogAspect {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Around("execution(public com.example.pojo.Result com.example.controller.LoginController.login(com.example.pojo.Emp))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        EmpLoginLog empLoginLog = new EmpLoginLog();
        Emp emp = (Emp) joinPoint.getArgs()[0];
        empLoginLog.setPassword(emp.getPassword());
        empLoginLog.setUsername(emp.getUsername());
        empLoginLog.setCostTime(costTime);
        empLoginLog.setLoginTime(java.time.LocalDateTime.now());

        Result resultObj = (Result) result;
        empLoginLog.setIsSuccess(Short.valueOf(String.valueOf(resultObj.getCode())));

        LoginInfo loginInfo = (LoginInfo) resultObj.getData();
        if (loginInfo != null){
            empLoginLog.setJwt(loginInfo.getToken());//如果登录成功jwt
        }

        log.info("登录日志：{}",empLoginLog);
        loginLogMapper.insert(empLoginLog);
        return result;
    }
}
