package com.example.mapper;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id = #{id}")
    Clazz queryById(Integer id);

    void update(Clazz clazz);

    @Select("select c.* from clazz c order by update_time desc")
    List<Clazz> queryAll();
}
