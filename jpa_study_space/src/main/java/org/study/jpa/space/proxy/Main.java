package org.study.jpa.space.proxy;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        insert(em);
        findStudent(em);
        deleteClassroom(em);

        insert(em);
        deleteStudent(em);
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

    public static void findStudent(EntityManager em){
        // 즉시 로딩을 경우 student와 관련된 classroom도 함께 조회함.
        // 지연 로딩일 경우 Student 데이터만 조회함.
        Student findStudent = em.find(Student.class,2L);
        System.out.println("findStudent =================== "+ findStudent.getName());

        // Lazy로딩일 경우 classroom 가져오는 쿼리가 여기서 호출됨.
        findStudent.getClassroom().getName();
    }

    public static void deleteClassroom(EntityManager em){

        Classroom findClassroom = em.find(Classroom.class, 1L);
        em.remove(findClassroom);
        em.flush();
    }

    public static void deleteStudent(EntityManager em){
        Classroom classroom = em.find(Classroom.class , 4L);
        classroom.getStudentList().remove(0);
    }
}
