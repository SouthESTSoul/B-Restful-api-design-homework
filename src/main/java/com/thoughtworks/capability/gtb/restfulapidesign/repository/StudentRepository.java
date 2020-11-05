package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepository {

    public AtomicInteger studentNumber=new AtomicInteger(studentDB.size());
    public static final int GROUPCOUNTS=6;
    public static CopyOnWriteArrayList<Student> studentDB=new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<StudentGroup> studentGroups=new CopyOnWriteArrayList<StudentGroup>();



}
