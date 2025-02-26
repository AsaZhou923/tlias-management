package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.StuQueryParam;
import com.example.pojo.Student;

import java.util.List;

public interface StuService {

    PageResult page(StuQueryParam stuQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student queryById(Integer id);

    void update(Student student);

    void violate(Integer id, Integer score);
}
