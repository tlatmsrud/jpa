package org.study.jpa.space.relationmapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RelationMethodBug {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        useRelationMethod(em);
        tx.commit();
    }

    public static void useRelationMethodWithBug(EntityManager em){

        TTeam tTeam1 = new TTeam();
        tTeam1.setId("team1");
        tTeam1.setName("팀1");
        em.persist(tTeam1);

        TTeam tTeam2 = new TTeam();
        tTeam2.setId("team2");
        tTeam2.setName("팀2");
        em.persist(tTeam2);

        TMember tMember1 = new TMember();
        tMember1.setId("user1");
        tMember1.setUsername("유저1");
        tMember1.setTeam(tTeam1);
        tMember1.setTeam(tTeam2);
        em.persist(tMember1);

        TTeam findTeam = em.find(TTeam.class, "team1");
        List<TMember> findMembers = findTeam.getMembers();

        for(TMember tMember : findMembers){
            // team1에 Member가 없어야하지만 tMemeber1이 조회됨.
            System.out.println("member.username = "+ tMember.getUsername());
        }
    }

    public static void useRelationMethod(EntityManager em){

        TTeam tTeam1 = new TTeam();
        tTeam1.setId("team1");
        tTeam1.setName("팀1");
        em.persist(tTeam1);

        TTeam tTeam2 = new TTeam();
        tTeam2.setId("team2");
        tTeam2.setName("팀2");
        em.persist(tTeam2);

        TMember tMember1 = new TMember();
        tMember1.setId("user1");
        tMember1.setUsername("유저1");
        tMember1.standardSetTeam(tTeam1);
        tMember1.standardSetTeam(tTeam2);
        em.persist(tMember1);

        TTeam findTeam = em.find(TTeam.class, "team1");
        List<TMember> findMembers = findTeam.getMembers();

        for(TMember tMember : findMembers){
            System.out.println("member.username = "+ tMember.getUsername());
        }
    }
}