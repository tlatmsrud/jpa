package org.study.jpa.space.proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Student student1 = new Student();
        student1.setName("심승경");
        em.persist(student1);

        Student student2 = new Student();
        student2.setName("홍길동");
        em.persist(student2);

        Classroom classroom = new Classroom();
        classroom.setName("배드민턴반");
        em.persist(classroom);

        student1.setClassroom(classroom);
        classroom.addStudent(student2);

        List<Student> list = classroom.getStudentList();
        for(Student student : list){
            System.out.println("========================="+student.getName());
        }

        tx.commit();
        em.clear();

        Student findStudent = em.getReference(Student.class,1L);
        System.out.println("findStudent.getName 호출 전");
        findStudent.getName();

        em.close();
        emf.close();


    }
}
