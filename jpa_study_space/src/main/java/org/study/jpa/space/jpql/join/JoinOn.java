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
public class JoinOn {

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

        em.flush();
        em.clear();
    }
    public static void logic(EntityManager em){

        /**
         * select
         *                 student0_.student_id as student_1_10_,
         *                 student0_.class_id as class_id3_10_,
         *                 student0_.name as name2_10_
         *         from
         *             student student0_
         *         left outer join
         *             classroom classroom1_
         *                 on (
         *                     classroom1_.name=?
         *                 )
         *   student와 classroom을 classroom.name으로 left 조인 : 모든 Student가 조회됨.
         */
        String query = "SELECT s FROM Student s LEFT JOIN Classroom c on c.name = :classroomName";

        List<Student> studentList = em.createQuery(query, Student.class)
                .setParameter("classroomName","배드민턴2반")
                .getResultList();

        for(Student student : studentList){
            System.out.println(student.getName());
        }

        /**
         *          select
         *                 student0_.student_id as student_1_10_,
         *                 student0_.class_id as class_id3_10_,
         *                 student0_.name as name2_10_
         *         from
         *             student student0_
         *         inner join
         *             classroom classroom1_
         *                 on (
         *                     classroom1_.name=?
         *                 )
         *
         *       student와 classroom을 classroom.name으로 inner join = classroom.name 조건이 맞는 student가 조회됨 > where과 똑같음.
         *       그래서 일반적으로 inner join일 경우 on을 사용하지 않고 where 을 사용함.
         */
        String query2 = "SELECT s FROM Student s JOIN Classroom c on c.name = :classroomName";
        List<Student> studentList2 = em.createQuery(query2, Student.class)
                .setParameter("classroomName","배드민턴반")
                .getResultList();

    }

}
