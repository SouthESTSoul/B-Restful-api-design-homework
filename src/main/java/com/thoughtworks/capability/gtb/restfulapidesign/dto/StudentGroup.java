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
    int id;
    String groupName;
    String note;
    List<Student> students =new ArrayList<>();

    public StudentGroup(int id,String groupName) {
        this.id=id;
        this.groupName = groupName;
    }

    public void add(Student student){
        students.add(student);
    }
}
