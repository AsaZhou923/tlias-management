package com.example.service.impl;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.pojo.PageResult;
import com.example.service.OperateLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<OperateLog> operateLogs = operateLogMapper.list(page,pageSize);

        Page<OperateLog> p = (Page<OperateLog>) operateLogs;

        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
