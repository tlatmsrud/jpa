package org.study.jpa.space.proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SqlResultSetMapping(name = "nameWithClassroomName",
entities = {@EntityResult(entityClass = Student.class)},
columns = {@ColumnResult(name = "ClassroomName")})
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID", nullable = false)
    private Classroom classroom;

    public void setClassroom(Classroom classroom){
        if(this.classroom != null){
            this.classroom.getStudentList().remove(this);
        }
        this.classroom = classroom;
        classroom.getStudentList().add(this);
    }
}
