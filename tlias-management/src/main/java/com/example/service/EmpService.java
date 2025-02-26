package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> Page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> queryAll();

    LoginInfo login(Emp emp);
    //PageResult Page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);
}
