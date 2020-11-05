package com.thoughtworks.capability.gtb.restfulapidesign.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentGroup;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class StudentServiceImpl implements StudentService {

    // TODO GTB-知识点: - 没有使用钻石操作符
    public static CopyOnWriteArrayList<Student> studentDB=new CopyOnWriteArrayList<Student>();

    public static CopyOnWriteArrayList<StudentGroup> studentGroups=new CopyOnWriteArrayList<StudentGroup>();

    // TODO GTB-知识点: - 不符合三层架构，缺少用于保存数据的Repository层
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
    public StudentServiceImpl() {
        initStudentDB();
        // TODO GTB-完成度: - 应该在点击分组的时候才生成组
        initStudentGroups();
    }

    public void initStudentGroups(){
        studentGroups.clear();
        // TODO GTB-工程实践: - Magic Number
        int groups=6;
        // TODO GTB-知识点: - 以下代码可以使用Java8 Stream简化
        for (int i=0;i<groups;i++){
        studentGroups.add(new StudentGroup((1+i)+" 组"));
        }
        Collections.sort(studentGroups, new Comparator<StudentGroup>() {
            @Override
            public int compare(StudentGroup o1, StudentGroup o2) {
                return o1.getGroupName().compareTo(o2.getGroupName());
            }
        });
    }
    @Override
    public List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.addAll(studentDB);
        sortListById(students);
        return students;
    }

    @Override
    public void groupStudentRandomly() {
        initStudentGroups();
        ArrayList<Student> students = randomSortStudent();
        // TODO GTB-工程实践: - 列表遍历推荐使用for循环
        int i=0;
        while (i<students.size()){
            StudentGroup studentGroup = studentGroups.get(i % studentGroups.size());
            studentGroup.add(students.get(i));
            sortListById(studentGroup.getStudents());
            i++;
        }
        System.out.println(studentGroups.toString());
    }

    //Knuth-Durstenfeld Shuffle
    // TODO GTB-知识点: - 打乱顺序可以了解下Collections.shuffle()
    public ArrayList<Student> randomSortStudent(){
        ArrayList<Student> result = new ArrayList<>();
        Student[] randomStudents =new Student[studentDB.size()];
        randomStudents= studentDB.toArray( randomStudents );
        int studentCount=randomStudents.length;
        for(int i=0;i<studentCount;i++){
            int r =new Random().nextInt(studentCount-i);
            Student temp=randomStudents[i];
            randomStudents[i]= randomStudents[r];
            randomStudents[r]=temp;
        }
        Collections.addAll(result,randomStudents);
        return result;
    }
    public void sortListById(List<Student> list){
        // TODO GTB-知识点: - 了解下Comparator的静态方法
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId()-o2.getId();
            }
        });
    }

    @Override
    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    @Override
    // TODO GTB-工程实践: - 计算id的方式不够健壮，可以使用字段保存最大id
    public void addStudent(Student student) {
        int size = studentDB.size();
        student.setId(size+1);
        studentDB.add(student);
    }
}
