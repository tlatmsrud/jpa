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
public class FetchJoin {

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
    public static void logic(EntityManager em) {

        /**
         * fetch join은 연관된 엔티티나 컬렉션을 한번에 같이 조회하는 기능이다.
         *
         * fetch join
         * select
         *             student0_.student_id as student_1_10_0_,
         *             classroom1_.classroom_id as classroo1_3_1_,
         *             student0_.class_id as class_id3_10_0_,
         *             student0_.name as name2_10_0_,
         *             classroom1_.name as name2_3_1_
         *         from
         *             student student0_
         *         left outer join
         *             classroom classroom1_
         *                 on student0_.class_id=classroom1_.classroom_id
         * ---------------------------
         * left join
         * select
         *             student0_.student_id as student_1_10_,
         *             student0_.class_id as class_id3_10_,
         *             student0_.name as name2_10_
         *         from
         *             student student0_
         *         left outer join
         *             classroom classroom1_
         *                 on student0_.class_id=classroom1_.classroom_id
         *
         *     ...
         *     Student 엔티티의 Classroom은 즉시로딩 정책이므로 classroom 엔티티를 영속성 컨텍스트에 넣기 위한 쿼리가 실행됨
         *     select
         *         classroom0_.classroom_id as classroo1_3_0_,
         *         classroom0_.name as name2_3_0_
         *     from
         *         classroom classroom0_
         *     where
         *         classroom0_.classroom_id=?
         *                 */

        String query = "SELECT s FROM Student s LEFT JOIN FETCH s.classroom";

        List<Student> studentList = em.createQuery(query, Student.class)
                .getResultList();

        //

    }
}
