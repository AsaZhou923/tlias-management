package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.mapper.StuMapper;
import com.example.pojo.ClazzOption;
import com.example.pojo.JobOption;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StuMapper stuMapper;

    @Override
    public JobOption getEmpJobData() {
        //调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        //组装结果，并返回
        List<Object> jobList = list.stream().map(datamap -> datamap.get("pos")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return stuMapper.countStuDegreeData();
    }

    @Override
    public ClazzOption getStuClazzData() {
        List<Map<String,Object>> list = stuMapper.countStuClazzData();

        List<Object> clazzList = list.stream().map(datamap -> datamap.get("name")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();

        return new ClazzOption(clazzList,dataList);
    }
}
