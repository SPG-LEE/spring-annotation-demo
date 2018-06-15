package com.spring.demo.controller;

import com.spring.demo.bean.StudentBean;
import com.spring.demo.entity.Class;
import com.spring.demo.entity.Grade;
import com.spring.demo.entity.Student;
import com.spring.demo.service.ClassService;
import com.spring.demo.service.GradeService;
import com.spring.demo.service.StudentService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.*;

@RestController
@RequestMapping("demo/grades")
@Validated
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;

    @GetMapping
    public List<Grade> findAll() {
        return gradeService.findAll();
    }

    @GetMapping("/{gradeId}")
    public Grade findAll(@PathVariable @Range(min = 1,max = 10000) long gradeId) {
        return gradeService.findById(gradeId);
    }

    @GetMapping("/{gradeId}/class/{classId}")
    public Class findAll(@PathVariable long gradeId, @PathVariable long classId) {
        return classService.findById(classId);
    }
    @GetMapping("/{gradeId}/class/{classId}/student/{studentId}")
    public Student findAll(@PathVariable long gradeId, @PathVariable long classId, @PathVariable long studentId) {
        return studentService.findById(studentId);
    }
    @GetMapping("/{gradeId}/class/{classId}/students")
    public List<StudentBean> findClassStudents(@PathVariable long gradeId, @PathVariable long classId) {
        System.out.println("0--");
        return studentService.findByClassId(classId);
    }
    @DeleteMapping("/{gradeId}")
    public void delete(@PathVariable long gradeId) {
        gradeService.delete(gradeService.findById(gradeId));
    }

    @DeleteMapping("/{gradeId}/class/{classId}")
    public void delete(@PathVariable long gradeId, @PathVariable long classId) {
        classService.delete(classService.findById(classId));
    }

    @DeleteMapping("/{gradeId}/class/{classId}/student/{studentId}")
    public void delete(@PathVariable long gradeId, @PathVariable long classId, @PathVariable long studentId) {
        studentService.delete(studentService.findById(studentId));
    }

    @GetMapping("/init")
    public void init() {
        String[] gradeName = {"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"};
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String[] className = {"一班", "二班", "三班", "四班"};
            Grade singleGrade = new Grade();
            singleGrade.setName(gradeName[i]);
            singleGrade.setNumber(gradeName[i]);
            gradeService.save(singleGrade);
            Set<Class> classes = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                Class singleClass = new Class();
                singleClass.setName(className[j]);
                singleClass.setNumber(className[j]);
                singleClass.setGrade(singleGrade);
                classService.save(singleClass);
                Set<Student> students = new HashSet<>();
                for (int k = 0; k < 6; k++) {
                    String[] firstName = {"张", "王", "李", "赵", "马", "刘"};
                    String[] lastName = {"大", "二", "三", "四", "五", "六"};
                    Student singleStudent = new Student();
                    singleStudent.setClasses(singleClass);
                    singleStudent.setName(firstName[new Random().nextInt(5)] + lastName[new Random().nextInt(5)]);
                    students.add(singleStudent);
                }
                singleClass.setStudents(students);
                classes.add(singleClass);
            }
            singleGrade.setClasses(classes);
            grades.add(singleGrade);
        }
        gradeService.saveAll(grades);
    }
}
