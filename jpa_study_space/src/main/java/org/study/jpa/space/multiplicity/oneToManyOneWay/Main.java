package org.study.jpa.space.multiplicity.oneToManyOneWay;

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

        MPMember member1 = new MPMember();
        member1.setUsername("member1");
        MPMember member2 = new MPMember();
        member2.setUsername("member2");

        MPTeam team1 = new MPTeam();

        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1); // INSERT MEMBER1
        em.persist(member2); // INSERT MEMBER2
        em.persist(team1); // INSERT TEAM1, UPDATE MEMBER1, UPDATE MEMBER2
    }
}
