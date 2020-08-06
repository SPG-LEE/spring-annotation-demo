package com.spring.demo.service;

import com.spring.demo.bean.search.GetRequestBase;
import com.spring.demo.entity.Grade;

import java.util.List;

public interface GradeService {
    void save(Grade entity);

    void saveAll(List<Grade> entitys);

    void delete(Grade entity);

    List<Grade> findAll();

    List<Grade> findAll(GetRequestBase getRequestBase);

    Grade findById(long id);

    void mergeTest(long gradeId, Grade grade);
}
