package com.spring.demo.service.impl;

import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.service.ClassService;
import com.spring.demo.entity.Class;
import com.spring.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public void save(Class entity) {
        classRepository.save(entity);
    }

    @Override
    public void saveAll(List<Class> entitys) {
        classRepository.saveAll(entitys);
    }

    @Override
    public void delete(Class entity) {
        entity.setEntityStatus(EntityStatus.DELETE);
        classRepository.save(entity);
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class findById(long id) {
        Optional<Class> byId = classRepository.findById(id);
        if (byId != null && byId.isPresent()) {
            return byId.get();
        }
        return null;
    }
}
