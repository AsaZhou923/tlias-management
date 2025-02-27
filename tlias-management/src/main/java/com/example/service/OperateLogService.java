package com.example.service;

import com.example.pojo.PageResult;

public interface OperateLogService {
    PageResult page(Integer page, Integer pageSize);
}
