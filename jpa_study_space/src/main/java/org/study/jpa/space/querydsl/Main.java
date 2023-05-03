package org.study.jpa.space.querydsl;


import com.querydsl.jpa.impl.JPAQuery;
import org.study.jpa.space.objectmapping.Member;
import org.study.jpa.space.objectmapping.QMember;

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

        tx.begin();

        try{
            JPAQuery<Member> query = new JPAQuery(em);
            QMember qMember = new QMember("m");

            List<Member> members =query.from(qMember)
                    .where(qMember.username.eq("test0"))
                    .orderBy(qMember.username.asc())
                    .fetch();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
