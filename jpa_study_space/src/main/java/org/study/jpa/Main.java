package org.study.jpa;


import org.study.jpa.main.TestMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

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

        TestMember member = new TestMember();
        member.setUsername("테스터");
        member.setAge(2);

        em.persist(member);

        member.setAge(20);

        TestMember testMember = em.find(TestMember.class, member.getId());
        System.out.println("findMember = " + testMember.getUsername() + ", "+ testMember.getAge());

        List<TestMember> testMembers = em.createQuery("select m from TestMember m", TestMember.class)
                .getResultList();

        System.out.println("size : "+ testMembers.size());

        em.remove(member);

    }

}