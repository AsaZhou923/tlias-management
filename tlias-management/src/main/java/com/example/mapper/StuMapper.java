package com.example.mapper;

import com.example.pojo.StuQueryParam;
import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {

    List<Map<String, Object>> countStuClazzData();

    public List<Student> list(StuQueryParam stuQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    @Select("select s.* from student s where s.id = #{id}")
    Student queryById(Integer id);

    void update(Student student);

    @Update("update student s set s.violation_score = s.violation_score + #{score},s.violation_count = s.violation_count + 1 where id = #{id}")
    void violate(Integer id, Integer score);

    List<Map<String, Object>> countStuDegreeData();
}
