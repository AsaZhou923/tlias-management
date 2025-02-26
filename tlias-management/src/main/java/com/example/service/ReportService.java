package com.example.service;

import com.example.pojo.ClazzOption;
import com.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStuDegreeData();

    ClazzOption getStuClazzData();
}
