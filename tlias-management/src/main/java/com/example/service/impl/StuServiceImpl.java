package com.example.service.impl;

import com.example.mapper.StuMapper;
import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.pojo.StuQueryParam;
import com.example.pojo.Student;
import com.example.service.StuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public void delete(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    @Override
    public PageResult page(StuQueryParam stuQueryParam) {
        PageHelper.startPage(stuQueryParam.getPage(),stuQueryParam.getPageSize());

        List<Student> stuList = stuMapper.list(stuQueryParam);

        Page<Student> p = (Page<Student>) stuList;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.insert(student);
    }

    @Override
    public Student queryById(Integer id) {
        return stuMapper.queryById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override
    public void violate(Integer id, Integer score) {
        stuMapper.violate(id,score);
    }
}
