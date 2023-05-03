package org.study.jpa;

import org.study.jpa.space.objectmapping.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudySpace");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();

        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

    public static void logic(EntityManager em){

        Member member = new Member();
        member.setUsername("테스터");
        member.setAge(2);

        em.persist(member);

        member.setAge(20);

        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember.getUsername() + ", "+ findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        System.out.println("size : "+ members.size());

        em.remove(member);

    }
}