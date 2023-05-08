package org.study.jpa.space.querydsl;


import com.querydsl.jpa.impl.JPAQuery;
import org.study.jpa.main.QTestMember;
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

        tx.begin();

        try{
            JPAQuery<TestMember> query = new JPAQuery(em);
            QTestMember qMember = new QTestMember("m");

            List<TestMember> testMembers =query.from(qMember)
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
