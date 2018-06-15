package com.spring.demo.mapper;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Results(id = "studentMap", value = {
            @Result(property = "studentName", column = "studentName"),
            @Result(property = "gradeName", column = "gradeName"),
            @Result(property = "className", column = "className")
    })
    @Select("select student.name as studentName,class.name as className,grade.name as gradeName from students student left join classes class on class.id=student.classId left join grades grade on grade.id = class.gradeId")
    List<StudentBean> findAll();

    @ResultMap("studentMap")
    @Select("select student.name as studentName,class.name as className,grade.name as gradeName from students student left join classes class on class.id=student.classId left join grades grade on grade.id = class.gradeId where class.id = #{classId}")
    List<StudentBean> findByClassId(@Param("classId") long classId);
}
