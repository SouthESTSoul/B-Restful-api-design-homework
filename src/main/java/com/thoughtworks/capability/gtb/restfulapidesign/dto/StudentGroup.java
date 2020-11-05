package com.thoughtworks.capability.gtb.restfulapidesign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {
    String groupName;
    List<Student> students =new ArrayList<>();

    public StudentGroup(String groupName) {
        this.groupName = groupName;
    }

    public void add(Student student){
        students.add(student);
    }
}
