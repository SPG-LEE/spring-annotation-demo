package com.spring.demo.service.impl;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.mapper.StudentMapper;
import com.spring.demo.service.StudentService;
import com.spring.demo.entity.Student;
import com.spring.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void saveAll(List<Student> entitys) {
        studentRepository.saveAll(entitys);
    }

    @Override
    public void delete(Student entity) {
        entity.setEntityStatus(EntityStatus.DELETE);
        studentRepository.save(entity);
    }

    @Override
    public List<StudentBean> findAll() {
//        return studentRepository.findAll();
        return studentMapper.findAll();
    }

    @Override
    public List<StudentBean> findByClassId(long classId) {
//        return studentRepository.findAll();
        return studentMapper.findByClassId(classId);
    }

    @Override
    public Student findById(long id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId != null && byId.isPresent()) {
            return byId.get();
        }
        return null;
    }
}
