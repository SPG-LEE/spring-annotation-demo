package com.spring.demo.service.impl;

import com.spring.demo.entity.Grade;
import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.repository.GradeRepository;
import com.spring.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public void save(Grade entity) {
        gradeRepository.save(entity);
    }

    @Override
    public void saveAll(List<Grade> entitys) {
        gradeRepository.saveAll(entitys);
    }

    @Override
    public void delete(Grade entity) {
        entity.setEntityStatus(EntityStatus.DELETE);
        gradeRepository.save(entity);
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findById(long id) {
        Optional<Grade> byId = gradeRepository.findById(id);
        if (byId != null && byId.isPresent()) {
            return byId.get();
        }
        return null;
    }
}
