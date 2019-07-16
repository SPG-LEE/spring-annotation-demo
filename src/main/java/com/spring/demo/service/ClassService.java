package com.spring.demo.service;

import com.spring.demo.bean.ServiceResult;
import com.spring.demo.controller.search.bean.request.GetClassListRequest;
import com.spring.demo.entity.Class;

import java.util.List;

public interface ClassService {
    void save(Class entity);

    void saveAll(List<Class> entitys);

    void delete(Class entity);

    List<Class> findAll();

    ServiceResult<List<Class>> findAll(GetClassListRequest getClassListRequest);

    Class findById(long id);
}
