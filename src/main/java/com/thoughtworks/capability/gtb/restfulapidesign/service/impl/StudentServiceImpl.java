package com.thoughtworks.capability.gtb.restfulapidesign.service.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public void initStudentGroups() {
        StudentRepository.studentGroups.clear();
        int groupCounts = StudentRepository.GROUPCOUNTS;
        for (int i = 0; i < groupCounts; i++) {
            StudentRepository.studentGroups.add(new StudentGroup(i+1,(1 + i) + " 组"));
        }
        Collections.sort(StudentRepository.studentGroups, new Comparator<StudentGroup>() {
            @Override
            public int compare(StudentGroup o1, StudentGroup o2) {
                return o1.getGroupName().compareTo(o2.getGroupName());
            }
        });
    }

    @Override
    public List<Student> getStudents(String gender) {
        List<Student> students = new ArrayList<>();
        students.addAll(StudentRepository.studentDB);
        students.sort(Comparator.comparing(Student::getId));
        if (gender != null) {
            students=students.stream().filter(student -> student.getGender().equals(gender)).collect(Collectors.toList());
        }
        return students;
    }

    @Override
    public void groupStudentRandomly() {
        initStudentGroups();
        List<Student> students = randomSortStudent();
        for (int i = 0; i < students.size(); i++) {
            StudentGroup studentGroup = StudentRepository.studentGroups.get(i % StudentRepository.studentGroups.size());
            studentGroup.add(students.get(i));
            studentGroup.getStudents().sort(Comparator.comparing(Student::getId));
        }
    }

    public List<Student> randomSortStudent() {
        Random rand = new Random(47);
        List<Student> result = new ArrayList<>();
        result.addAll(StudentRepository.studentDB);
        Collections.shuffle(result);
        return result;
    }

    @Override
    public List<StudentGroup> getStudentGroups() {
        return StudentRepository.studentGroups;
    }

    @Override
    public Student addStudent(Student student) {
        student.setId(studentRepository.studentNumber.incrementAndGet());
        StudentRepository.studentDB.add(student);
        return student;
    }

    @Override
    public Student getStudentById(int id) {
        List<Student> studentsById = StudentRepository.studentDB.stream().filter(student -> student.getId() == id).collect(Collectors.toList());
        return studentsById.get(0);
    }

    @Override
    public void deleteStudentById(int id) {
        Student deletedStudent = StudentRepository.studentDB.stream().filter(student -> student.getId() == id).collect(Collectors.toList()).get(0);
        StudentRepository.studentDB.remove(deletedStudent);
        studentRepository.studentNumber.decrementAndGet();
    }

    @Override
    public StudentGroup modifyGroupNameById(int id, String name) {
        List<StudentGroup> studentGroupsById = StudentRepository.studentGroups.stream().filter(studentGroup -> studentGroup.getId() == id).collect(Collectors.toList());
        studentGroupsById.get(0).setGroupName(name);
        return studentGroupsById.get(0);

    }
}
