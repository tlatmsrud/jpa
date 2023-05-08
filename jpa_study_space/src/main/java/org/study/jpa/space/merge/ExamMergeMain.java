package org.study.jpa.space.merge;

import org.study.jpa.main.TestMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {


    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
    public static void main(String args[]){


        TestMember testMember = createMember("memberA", "회원1");

        // 준영속 상태라 setUsername이 먹히지 않음.
        testMember.setUsername("회원명변경");

        mergeMember(testMember);
    }

    // DB에 데이터 insert 후 em.close 하여 준영속된 member 객체를 리턴
    static TestMember createMember(String id, String username){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        TestMember testMember = new TestMember();
        testMember.setId(id);
        testMember.setUsername(username);

        em.persist(testMember);
        tx.commit();

        em.close();

        return testMember;
    }

    static void mergeMember(TestMember testMember){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 준영속 상태의 member를 영속상태로 변경
        //Member mergeMember = em.merge(member);
        testMember = em.merge(testMember);
        tx.commit();

        // 준영속 상태
        System.out.println("member = " + testMember.getUsername());

        // 영속상태
        System.out.println("mergeMember = " + testMember.getUsername());

        System.out.println("em contains member = " + em.contains(testMember));
        System.out.println("em contains mergeMember = " + em.contains(testMember));

        em.close();
    }
}
