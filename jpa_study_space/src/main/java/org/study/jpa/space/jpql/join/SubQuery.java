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
 * date         : 2023-05-25
 * description  :
 */
public class SubQuery {

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

        em.flush();
        em.clear();
    }
    public static void logic(EntityManager em) {

        String query = "SELECT s FROM Student s WHERE s.id > (select avg(s2.id) from Student s2)";

        List<Student> list = em.createQuery(query,Student.class)
                .getResultList();

        for(Student student : list){
            System.out.println(student.getName());
        }

    }
}
