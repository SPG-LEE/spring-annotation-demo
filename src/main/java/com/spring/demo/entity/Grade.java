package com.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.demo.enumerate.EntityStatus;
import com.spring.demo.enumerate.TransportType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@Entity
@Table(name = "grades")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update grades set entityStatus=" +
        "'DELETE' where id=?")
@Where(clause = "entityStatus<> 'DELETE'")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Grade {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String number;
    private Set<Class> classes = new HashSet<>();
    @JsonProperty
    private EntityStatus entityStatus = EntityStatus.ENABLE;
    @JsonProperty
    private Date createDate = new Date();
    @JsonProperty
    private Timestamp createDateTime;
    @JsonProperty
    private TransportType transportType = TransportType.AIR;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Timestamp getCreateDateTime() {
        return Timestamp.valueOf(parseDateByTimeZone(createDate,"yyyy-MM-dd HH:mm:ss","GMT+8"));
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = Timestamp.valueOf(parseDateByTimeZone(createDate,"yyyy-MM-dd HH:mm:ss","GMT+21"));
    }

    public String parseDateByTimeZone(Date date, String patten, String timeZone) {
        DateFormat df = new SimpleDateFormat(patten);
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
        return df.format(date);
    }

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(length = 16)
    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
}
