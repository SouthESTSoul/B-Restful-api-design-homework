package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String gender) {

        List<Student> students = studentService.getStudents(gender);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/students/groups")
    public ResponseEntity groupStudentRandomly() throws URISyntaxException {
        studentService.groupStudentRandomly();
        return ResponseEntity.created(new URI("students/groups")).build();
    }

    @GetMapping("/students/groups")
    public ResponseEntity<List<StudentGroup>> getStudentsGroups() {
        List<StudentGroup> studentGroups = studentService.getStudentGroups();
        return ResponseEntity.ok().body(studentGroups);

    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws URISyntaxException {
        Student finshAddedStudent = studentService.addStudent(student);
        return ResponseEntity.created(new URI("/students")).body(finshAddedStudent);
    }

    @PatchMapping("/students/groups")
    public ResponseEntity<StudentGroup> modifyGroupNameById(@RequestParam int id, @RequestParam String name) {
        StudentGroup studentGroup = studentService.modifyGroupNameById(id, name);
        return ResponseEntity.ok().body(studentGroup);
    }
}
