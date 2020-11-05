package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(String gender);
    void groupStudentRandomly();
    List<StudentGroup>getStudentGroups();
    Student addStudent(Student student);
    Student getStudentById(int id);
    void deleteStudentById(int id);
    StudentGroup modifyGroupNameById(int id,String name);
}
