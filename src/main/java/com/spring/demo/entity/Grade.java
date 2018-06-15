package com.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.demo.enumerate.EntityStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grades")
@DynamicInsert
@DynamicUpdate
@Where(clause = "entityStatus<> 'DELETE'")
public class Grade {
    private long id;
    private String name;
    private String number;
    private Set<Class> classes = new HashSet<>();
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

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "grade")
    @Where(clause = "entityStatus<> 'DELETE'")
    @OrderBy("id asc")
    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    @Enumerated(EnumType.STRING)
    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
