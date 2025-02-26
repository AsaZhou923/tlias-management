package com.example.exception;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器异常：", e);
        return Result.error("服务器发生异常，请联系管理员" );
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库操作异常：", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        String msg = "操作异常，" + arr[2] + "已存在";
        return Result.error(msg);
    }

    @ExceptionHandler(DeptNotEmptyException.class)
    public Result handleDeptNotEmptyException(DeptNotEmptyException ex){
        return Result.error(ex.getMessage());
    }
}
