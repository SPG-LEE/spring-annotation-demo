package com.spring.demo.service.impl;

import com.spring.demo.bean.ServiceResult;
import com.spring.demo.bean.ServiceResultBuilder;
import com.spring.demo.controller.search.bean.request.GetClassListRequest;
import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.mapper.ClassMapper;
import com.spring.demo.service.ClassService;
import com.spring.demo.entity.Class;
import com.spring.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private ClassMapper classMapper;

    @Override public void addMark(long id, long count) {
        classMapper.addMark(id, count);
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override public ServiceResult<List<Class>> findAll(GetClassListRequest getClassListRequest) {
        List<Class> findClassList = classRepository.findByGradeId(getClassListRequest.getGradeId(), PageRequest.of(getClassListRequest.getPageIndex(), getClassListRequest.getPageSize()));
        long total = classRepository.countByGradeId(getClassListRequest.getGradeId());
        return ServiceResultBuilder.buildSuccessResult(findClassList, "123", total);
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
