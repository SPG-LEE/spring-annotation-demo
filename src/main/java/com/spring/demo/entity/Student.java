package com.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.demo.enumerate.EntityStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "students")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update students set entityStatus=" +
        "'DELETE' where id=?")
@Where(clause = "exists (select id from classes class left join grades grade on grade.id=class.gradeId where class.id = classId and grade.entityStatus<> 'DELETE'and class.entityStatus<> 'DELETE')")
public class Student {
    private long id;
    private String name;
    private Class classes;
    private EntityStatus entityStatus = EntityStatus.ENABLE;

    @ManyToOne
    @JoinColumn(name = "classId")
    @JsonIgnoreProperties({"students"})
    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class classes) {
        this.classes = classes;
    }

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
    @Enumerated(EnumType.STRING)
    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
