package com.spring.demo.service;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);

    void saveAll(List<Student> students);

    void delete(Student student);

    List<StudentBean> findAll();

    List<StudentBean> findByClassId(long classId);

    Student findById(long id);
}
