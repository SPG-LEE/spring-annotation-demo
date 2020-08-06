package com.spring.demo.service.impl;

import com.spring.demo.bean.search.GetRequestBase;
import com.spring.demo.entity.Grade;
import com.spring.demo.repository.GradeRepository;
import com.spring.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public void saveAll(List<Grade> entities) {
        gradeRepository.saveAll(entities);
    }

    @Override
    public void delete(Grade entity) {
        gradeRepository.delete(entity);
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public List<Grade> findAll(GetRequestBase getRequestBase) {
        return gradeRepository.findAll(PageRequest.of(getRequestBase.getPageIndex(), getRequestBase.getPageSize())).getContent();
    }

    @Override
    public Grade findById(long id) {
        Optional<Grade> byId = gradeRepository.findById(id);
        if (byId != null && byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public void mergeTest(long gradeId, Grade grade) {
        if (grade.getId() != null && grade.getId().longValue() == gradeId){
            gradeRepository.save(grade);
        }
    }
}
