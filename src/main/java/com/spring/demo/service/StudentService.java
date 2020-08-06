package com.spring.demo.service;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    void save(Student student) throws InterruptedException, IOException;

    void saveWithoutTransaction(Student student) throws InterruptedException;

    void saveAll(List<Student> students);

    void delete(Student student);

    List<StudentBean> findAll();

    List<Student> findAll(int pageIndex,int pageSize);

    List<StudentBean> findByClassId(long classId);

    Student findById(long id);

    Student findByName(String name);
}
