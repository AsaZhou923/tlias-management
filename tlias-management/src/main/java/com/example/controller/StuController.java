package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.StuQueryParam;
import com.example.pojo.Student;
import com.example.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping
    public Result StuPage(StuQueryParam stuQueryParam){
        log.info("分页查询学生信息：{}", stuQueryParam);
        PageResult pageResult = stuService.page(stuQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("根据id删除学生：{}", ids);
        stuService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("保存学生信息：{}", student);
        stuService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("根据id查询学生信息：{}", id);
        Student student = stuService.queryById(id);
        return Result.success(student);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息：{}", student);
        stuService.update(student);
        return Result.success();
    }

    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violate(@PathVariable Integer id,@PathVariable Integer score){
        log.info("学生{}违规，扣除积分：{}", id, score);
        stuService.violate(id, score);
        return Result.success();
    }
}
