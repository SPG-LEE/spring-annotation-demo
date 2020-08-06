package com.spring.demo.controller.transaction;

import com.spring.demo.entity.Student;
import com.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("demo/transaction")
@Validated
@CrossOrigin(allowedHeaders = {"x-access-token"})
public class TransactionController {
    @Autowired
    private StudentService studentService;

    @PutMapping("/testTransaction")
    public void testTransaction(@RequestBody Student student) throws IOException, InterruptedException {
        studentService.save(student);
    }

    @PutMapping("/testWithoutTransaction")
    public void testWithoutTransaction(@RequestBody Student student) {
        try {
            studentService.saveWithoutTransaction(student);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable long id) {
        Student byId = studentService.findById(id);
        return byId;
    }
}
