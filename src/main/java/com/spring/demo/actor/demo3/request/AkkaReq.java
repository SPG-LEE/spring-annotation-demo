package com.spring.demo.actor.demo3.request;

import com.spring.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AkkaReq {
    private String data;
    private Student student;
}
