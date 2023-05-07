package org.study.jpa.space.merge;

import org.study.jpa.main.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {


    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
    public static void main(String args[]){


        Member member = createMember("memberA", "회원1");

        // 준영속 상태라 setUsername이 먹히지 않음.
        member.setUsername("회원명변경");

        mergeMember(member);
    }

    // DB에 데이터 insert 후 em.close 하여 준영속된 member 객체를 리턴
    static Member createMember(String id, String username){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em.persist(member);
        tx.commit();

        em.close();

        return member;
    }

    static void mergeMember(Member member){

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 준영속 상태의 member를 영속상태로 변경
        //Member mergeMember = em.merge(member);
        member = em.merge(member);
        tx.commit();

        // 준영속 상태
        System.out.println("member = " + member.getUsername());

        // 영속상태
        System.out.println("mergeMember = " + member.getUsername());

        System.out.println("em contains member = " + em.contains(member));
        System.out.println("em contains mergeMember = " + em.contains(member));

        em.close();
    }
}
