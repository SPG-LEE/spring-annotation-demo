package com.spring.demo.repository;

import com.spring.demo.entity.Class;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.net.ContentHandler;
import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByGradeId(long gradeId, Pageable pageable);

    long countByGradeId(long gradeId);

    @Query("update Class set mark = mark+ :count where id = :id")
    void updateMark(@Param("id") long id, @Param("count") long count);
}
