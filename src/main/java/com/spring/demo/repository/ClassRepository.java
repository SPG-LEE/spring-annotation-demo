package com.spring.demo.repository;

import com.spring.demo.entity.Class;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ContentHandler;
import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByGradeId(long gradeId, Pageable pageable);

    long countByGradeId(long gradeId);
}
