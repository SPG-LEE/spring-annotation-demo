package com.spring.demo.repository;

import com.spring.demo.entity.Grade;
import com.spring.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
