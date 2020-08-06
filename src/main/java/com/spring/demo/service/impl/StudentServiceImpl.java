package com.spring.demo.service.impl;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.entity.Student;
import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.mapper.StudentMapper;
import com.spring.demo.repository.StudentRepository;
import com.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void save(Student entity) throws InterruptedException, IOException {
        Student student = studentMapper.findById(entity.getId());
        System.out.println("start1-1:"+student.getName());
        Thread.sleep(10000);
        student = studentMapper.findById(entity.getId());
//        RedisLockUtil.tryLock("Student-"+entity.getId(),12,10);
        System.out.println("start1-2:"+student.getName());
        student.setName(student.getName() + "-2");
        studentMapper.updateUser(student.getName(),student.getId());
        System.out.println("end1:"+student.getName());
//        RedisLockUtil.unlock("Student-"+entity.getId());
//        throw new IOException();
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void saveWithoutTransaction(Student entity) throws InterruptedException {
        Student student = studentMapper.findById(entity.getId());
//        RedisLockUtil.tryLock("Student-"+student.getId(),12,10);
        System.out.println("start2:"+student.getName());
        student.setName(student.getName() + "-1");
        studentMapper.updateUser(student.getName(),student.getId());
        System.out.println("end2:"+student.getName());
//        RedisLockUtil.unlock("Student-"+student.getId());
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
    public List<Student> findAll(int pageIndex, int pageSize) {
        return studentRepository.findAll(PageRequest.of(pageIndex, pageSize)).getContent();
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

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name).get(0);
    }
}
