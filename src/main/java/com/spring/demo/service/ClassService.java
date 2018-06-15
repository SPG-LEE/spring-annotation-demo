package com.spring.demo.service;

import com.spring.demo.entity.Class;

import java.util.List;

public interface ClassService {
    void save(Class entity);

    void saveAll(List<Class> entitys);

    void delete(Class entity);

    List<Class> findAll();

    Class findById(long id);
}
