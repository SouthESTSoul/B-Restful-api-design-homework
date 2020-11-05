package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepository {
    public StudentRepository( ) {
        initStudentDB();
    }

    public AtomicInteger studentNumber=new AtomicInteger(studentDB.size());
    public static final int GROUPCOUNTS=6;
    public static CopyOnWriteArrayList<Student> studentDB=new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<StudentGroup> studentGroups=new CopyOnWriteArrayList<StudentGroup>();
    public static void initStudentDB(){
        studentDB.clear();
        studentDB.add(new Student(1,"成吉思汗"));
        studentDB.add(new Student(2,"鲁班七号"));
        studentDB.add(new Student(3,"太乙真人"));
        studentDB.add(new Student(4,"钟无艳"));
        studentDB.add(new Student(5,"花木兰"));
        studentDB.add(new Student(6,"雅典娜"));
        studentDB.add(new Student(7,"芈月"));
        studentDB.add(new Student(8,"白起"));
        studentDB.add(new Student(9,"刘禅"));
        studentDB.add(new Student(10,"庄周"));
        studentDB.add(new Student(11,"马超"));
        studentDB.add(new Student(12,"刘备"));
        studentDB.add(new Student(13,"哪吒"));
        studentDB.add(new Student(14,"大桥"));
        studentDB.add(new Student(15,"蔡文姬"));
    };


}
