package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok().body(students);
    }


    @PostMapping("/students/groups")
    public ResponseEntity groupStudentRandomly() throws URISyntaxException {
        studentService.groupStudentRandomly();
        return ResponseEntity.created(new URI("students/groups")).build();

    }
    @GetMapping("/students/groups")
    public ResponseEntity<List<StudentGroup>> getStudentsGroups(){
        List<StudentGroup> studentGroups = studentService.getStudentGroups();
        return ResponseEntity.ok().body(studentGroups);

    }

    @PostMapping("/students")
    public ResponseEntity addStudent(@RequestBody Student student) throws URISyntaxException {
        studentService.addStudent(student);
        return ResponseEntity.created(new URI("/students")).build();
    }
}
