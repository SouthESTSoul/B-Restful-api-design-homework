package com.thoughtworks.capability.gtb.restfulapidesign.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.StudentService;
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

 private final StudentRepository studentRepository =new StudentRepository();



    public void initStudentGroups(){
        StudentRepository.studentGroups.clear();
        int groupCounts=StudentRepository.GROUPCOUNTS;
        for (int i=0;i<groupCounts;i++){
            StudentRepository.studentGroups.add(new StudentGroup((1+i)+" 组"));
        }
        Collections.sort(    StudentRepository.studentGroups, new Comparator<StudentGroup>() {
            @Override
            public int compare(StudentGroup o1, StudentGroup o2) {
                return o1.getGroupName().compareTo(o2.getGroupName());
            }
        });
    }
    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.addAll(StudentRepository.studentDB);
        students.sort(Comparator.comparing(Student::getId));
        return students;
    }

    @Override
    public void groupStudentRandomly() {
        initStudentGroups();
        List<Student> students = randomSortStudent();
        for(int i=0;i<students.size();i++){
            StudentGroup studentGroup = StudentRepository.studentGroups.get(i % StudentRepository.studentGroups.size());
            studentGroup.add(students.get(i));
            studentGroup.getStudents().sort(Comparator.comparing(Student::getId));
        }
    }

    public List<Student> randomSortStudent(){
        Random rand=new Random(47);
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
    public void addStudent(Student student) {
        student.setId(studentRepository.studentNumber.incrementAndGet());
        StudentRepository.studentDB.add(student);
    }

    @Override
    public Student getStudentById(int id) {
        List<Student> studentsById = StudentRepository.studentDB.stream().filter(student -> student.getId() == id).collect(Collectors.toList());
        return studentsById.get(0);
    }
}
