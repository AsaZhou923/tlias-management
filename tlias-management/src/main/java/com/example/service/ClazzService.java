package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz queryById(Integer id);

    void update(Clazz clazz);

    List<Clazz> queryAll();
}
