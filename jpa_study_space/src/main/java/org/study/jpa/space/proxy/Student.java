package org.study.jpa.space.proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private Classroom classroom;

    public void setClassroom(Classroom classroom){
        if(this.classroom != null){
            this.classroom.getStudentList().remove(this);
        }
        this.classroom = classroom;
        classroom.getStudentList().add(this);
    }
}
