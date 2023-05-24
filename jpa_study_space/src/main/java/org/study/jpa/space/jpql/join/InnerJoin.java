package org.study.jpa.space.jpql.join;

import org.study.jpa.entity.Member;
import org.study.jpa.space.proxy.Classroom;
import org.study.jpa.space.proxy.Student;

import javax.persistence.*;
import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-05-24
 * description  :
 */
public class InnerJoin {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        insert(em);
        logic(em);
        tx.commit();

        em.close();
        emf.close();


    }
    public static void insert(EntityManager em){
        Student student1 = new Student();
        student1.setName("심승경");

        Student student2 = new Student();
        student2.setName("홍길동");

        Classroom classroom = new Classroom();
        classroom.setName("배드민턴반");

        student1.setClassroom(classroom);
        classroom.addStudent(student2);

        em.persist(classroom);
        List<Student> list = classroom.getStudentList();
        for(Student student : list){
            System.out.println("========================="+student.getName());
        }

        em.flush();
        em.clear();
    }
    public static void logic(EntityManager em){

        String classroomName = "배드민턴반";
        String query = "SELECT s FROM Student s INNER JOIN s.classroom c WHERE c.name = :classroomName";

        List<Student> studentList = em.createQuery(query, Student.class)
                .setParameter("classroomName",classroomName)
                .getResultList();

        /*for(Student student : studentList){
            System.out.println(student.getName());
        }*/
    }
}
