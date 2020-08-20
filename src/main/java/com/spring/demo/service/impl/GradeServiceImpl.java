package com.spring.demo.service.impl;

import com.spring.demo.bean.search.GetRequestBase;
import com.spring.demo.entity.Grade;
import com.spring.demo.repository.GradeRepository;
import com.spring.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public Grade findById(long id) {
        Optional<Grade> byId = gradeRepository.findById(id);
        if (byId != null && byId.isPresent()) {
            return byId.get();
        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mergeTest(long gradeId, Grade grade) {
        Grade gradeDb = new Grade();
        Optional<Grade> byId = gradeRepository.findById(gradeId);
        if (byId != null && byId.isPresent()) {
            gradeDb = byId.get();
        }

        gradeDb.setName(grade.getName());
        gradeRepository.save(gradeDb);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mergeAll() {
        Page<Grade> all = gradeRepository.findAll(PageRequest.of(0, 10000));
        all.stream().forEach(grade->{
            grade.setName(System.currentTimeMillis()+"");
        });
        gradeRepository.saveAll(all);

    }
}
