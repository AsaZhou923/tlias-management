package com.example.service.impl;

import com.example.mapper.ClazzMapper;
import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());

        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        Page<Clazz>  p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public Clazz queryById(Integer id) {
        return clazzMapper.queryById(id);
    }

    @Override
    public List<Clazz> queryAll() {
        return clazzMapper.queryAll();
    }
}
