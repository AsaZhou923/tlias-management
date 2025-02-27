package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.OperateLogService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result list(@PathParam("page") Integer page,@PathParam("pageSize") Integer pageSize) {
        log.info("查询所有操作日志");
        PageResult pageResult = operateLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
