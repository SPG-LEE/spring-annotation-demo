package com.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.demo.enumerate.EntityStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classes")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update classes set entityStatus=" +
        "'DELETE' where id=?")
@Where(clause = "exists (select id from grades grade where grade.id = gradeId and grade.entityStatus<> 'DELETE')")
public class Class {
    private long id;
    private String name;
    private String number;
    private Set<Student> students = new HashSet<>();
    private Grade grade;
    private EntityStatus entityStatus = EntityStatus.ENABLE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "classes")
//    @Where(clause = "entityStatus<> 'DELETE'")
    @OrderBy("id desc")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "gradeId")
    @JsonIgnoreProperties({"classes"})
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Enumerated(EnumType.STRING)
    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
