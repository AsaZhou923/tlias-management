package com.example.controller;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result clazzPage(ClazzQueryParam clazzQueryParam){
        log.info("分页查询班级信息,{}",clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除班级信息：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("保存班级信息：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("根据id查询班级信息：{}", id);
        Clazz clazz = clazzService.queryById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updata(@RequestBody Clazz clazz){
        log.info("修改班级信息：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result queryAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.queryAll();
        return Result.success(clazzList);
    }
}
