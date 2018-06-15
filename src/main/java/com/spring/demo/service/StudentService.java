package com.spring.demo.service;

import com.spring.demo.entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);

    void saveAll(List<Student> students);

    void delete(Student student);

    List<Student> findAll();

    Student findById(long id);
}
