package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询员工信息，{}",empQueryParam);
        PageResult pageResult = empService.Page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("根据id删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息{}",id);
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result queryAll(){
        log.info("查询全部员工信息");
        List<Emp> empList = empService.queryAll();
        return Result.success(empList);
    }
}
