package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();
    void groupStudentRandomly();
    List<StudentGroup>getStudentGroups();
    void addStudent(Student student);
    Student getStudentById(int id);
}
