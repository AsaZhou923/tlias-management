package com.example.controller;

import com.example.pojo.ClazzOption;
import com.example.pojo.JobOption;
import com.example.pojo.Result;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderlist = reportService.getEmpGenderData();
        return Result.success(genderlist);
    }

    @GetMapping("/studentDegreeData")
    public Result getStuDegreeData(){
        log.info("统计学生学历人数");
        List<Map<String,Object>> degreeList = reportService.getStuDegreeData();
        return Result.success(degreeList);
    }

    @GetMapping("/studentCountData")
    public Result getStuClazzData(){
        log.info("统计学生班级人数");
        ClazzOption clazzOption = reportService.getStuClazzData();
        return Result.success(clazzOption);
    }
}
