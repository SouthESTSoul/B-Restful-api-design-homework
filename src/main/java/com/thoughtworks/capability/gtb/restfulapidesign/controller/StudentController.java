package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
// TODO GTB-知识点: - 构造器注入时可以省略@Autowired
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    // TODO GTB-知识点: - 没有使用泛型
    public ResponseEntity getStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok().body(students);
    }


    // TODO GTB-知识点: - 违反Restful实践, url不合理
    @PostMapping("/student/groups")
    public ResponseEntity groupStudentRandomly() throws URISyntaxException {
        studentService.groupStudentRandomly();
        return ResponseEntity.created(new URI("student/groups")).build();

    }
    @GetMapping("/student/groups")
    public ResponseEntity getStudentsGroups(){
        List<StudentGroup> studentGroups = studentService.getStudentGroups();
        return ResponseEntity.ok().body(studentGroups);

    }

    @PostMapping("/student")
    public ResponseEntity addStudent(@RequestBody Student student) throws URISyntaxException {
        studentService.addStudent(student);
        return ResponseEntity.created(new URI("/students")).build();
    }
}
