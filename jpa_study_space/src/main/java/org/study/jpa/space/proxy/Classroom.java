package org.study.jpa.space.proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Classroom {

    @Id
    @GeneratedValue
    @Column(name = "CLASSROOM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "classroom", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student){
        if(student.getClassroom() != null){
            student.getClassroom().getStudentList().remove(student);
        }
        student.setClassroom(this);
    }
}
