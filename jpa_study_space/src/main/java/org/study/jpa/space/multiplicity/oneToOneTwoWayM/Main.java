package org.study.jpa.space.multiplicity.oneToOneTwoWayM;

import org.study.jpa.space.multiplicity.oneToManyOneWay.MPTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
    }


    public static void logic(EntityManager em){

        MPMember member = new MPMember();
        member.setUsername("member");
        em.persist(member);

        MPLocker locker = new MPLocker();
        locker.setName("locker #1");
        em.persist(locker);

        member.setLocker(locker);

        MPMember getMember = locker.getMember();
        System.out.println("getMember.getUsername : "+getMember.getUsername());

    }
}
