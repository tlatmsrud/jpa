package org.study.jpa.space.bulk;


import org.study.jpa.entity.Member;
import org.study.jpa.space.proxy.Student;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            bulkOperationCaution(em);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void logic(EntityManager em){

        String sql = "update Student s set s.name= '벌크'";

        int resultCnt = em.createQuery(sql).executeUpdate(); //executeUpdate 사용 시 벌크연산 처리

        System.out.println(resultCnt);

        em.flush();
    }

    public static void bulkOperationCaution(EntityManager em){

        List<Student> list = em.createQuery("select s from Student s")
                .getResultList();

        for(Student student : list){
            System.out.println("before bulk : "+student.getName()); // before bulk : 기본
        }

        String sql = "update Student s set s.name= '수정'";
        int resultCnt = em.createQuery(sql).executeUpdate();

        for(Student student : list){
            System.out.println("after bulk : "+student.getName()); // // before bulk : 기본 (수정이 아님)
        }

        // 벌크 연산은 영속성 컨텍스트를 거치지 않기 때문. 해결방안은 em.refresh를 통해 DB를 다시 조회하거나, 벌크연산 먼저 수행하거나, 영속성 컨텍스트를 초기화해야함.

    }
}
