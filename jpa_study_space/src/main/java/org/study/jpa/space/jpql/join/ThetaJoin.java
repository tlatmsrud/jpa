package org.study.jpa.space.jpql.join;

import org.study.jpa.space.proxy.Classroom;
import org.study.jpa.space.proxy.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-05-24
 * description  :
 */
public class ThetaJoin {

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

        /**
         * 세타 조인을 사용하면 전혀 관련 없는 엔티티와도 조인할 수 있다.
         * select
         *             student0_.student_id as student_1_10_,
         *             student0_.class_id as class_id3_10_,
         *             student0_.name as name2_10_
         *         from
         *             student student0_ cross
         *         join
         *             classroom classroom1_
         *         where
         *             classroom1_.name=student0_.name
         */
        String query = "SELECT s FROM Student s, Classroom c WHERE c.name = s.name";

        List<Student> studentList = em.createQuery(query, Student.class)
                .getResultList();

        for(Student student : studentList){
            System.out.println(student.getName());
        }
    }
}
