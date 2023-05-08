package org.study.jpa.space.relationmapping;

import javax.persistence.*;
import java.util.List;

public class RelationMappingMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        /*save(em);
        update(em);
        deleteRelation(em);
        delete(em);*/

        tx.begin();
        biDirection(em);
        tx.commit();
    }

    public static void save(EntityManager em){
        TMember tMember = new TMember();
        tMember.setId("user1");
        tMember.setUsername("유저1");

        em.persist(tMember);

        TTeam tTeam = new TTeam();
        tTeam.setId("team1");
        tTeam.setName("팀1");

        em.persist(tTeam);

        tMember.setTeam(tTeam);
    }

    public static void update(EntityManager em){
        TTeam tTeam = new TTeam();
        tTeam.setId("team2");
        tTeam.setName("팀2");
        em.persist(tTeam);

        TMember tMember = em.find(TMember.class, "user1");
        tMember.setTeam(tTeam);
    }

    public static void deleteRelation(EntityManager em){
        TMember tMember = em.find(TMember.class, "user1");
        tMember.setTeam(null);
    }

    public static void delete(EntityManager em){
        TTeam tTeam = em.find(TTeam.class, "team2");
        em.remove(tTeam);
    }

    public static void biDirection(EntityManager em){
        TTeam tTeam1 = new TTeam();
        tTeam1.setId("team1");
        tTeam1.setName("팀1");

        em.persist(tTeam1);

        TMember tMember1 = new TMember();
        tMember1.setId("user1");
        tMember1.setUsername("유저1");

        TMember tMember2 = new TMember();
        tMember2.setId("user2");
        tMember2.setUsername("유저2");

        TMember tMember3 = new TMember();
        tMember3.setId("user3");
        tMember3.setUsername("유저3");

        em.persist(tMember1);
        em.persist(tMember2);
        em.persist(tMember3);

        tMember1.setTeam(tTeam1);
        tMember2.setTeam(tTeam1);
        tMember3.setTeam(tTeam1);

        TTeam findTeam = em.find(TTeam.class, "team1");

        List<TMember> findMembers = findTeam.getMembers();

        for(TMember tMember : findMembers){
            System.out.println("member.username = "+ tMember.getUsername());
        }
    }
}
