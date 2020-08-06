package com.spring.demo.actor.demo3.request;

import com.spring.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AkkaListReq {
    private List<String> data;
    private List<Student> students;

    public String readData() {
        if (data.isEmpty()) {
            return null;
        }
        String cur = data.get(0);
        data.remove(0);
        return cur;
    }

    public Student readStudent() {

        if (students.isEmpty()) {
            return new Student();
        }
        Student cur = students.get(0);
        students.remove(0);
        return cur;
    }

    public boolean hasNext() {
        return !data.isEmpty();
    }
}
