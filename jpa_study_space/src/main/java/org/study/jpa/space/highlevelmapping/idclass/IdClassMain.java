package org.study.jpa.space.highlevelmapping.idclass;

import com.querydsl.jpa.impl.JPAQuery;
import org.study.jpa.main.QTestMember;
import org.study.jpa.main.TestMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class IdClassMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            ICParent parent = new ICParent();
            parent.setId1("myId1");
            parent.setId2("myId2");
            parent.setName("parentName");
            em.persist(parent);

            em.flush();
            em.clear();

            ICParentId parentId = new ICParentId("myId1","myId2");
            ICParent findParent = em.find(ICParent.class, parentId);
            System.out.println(findParent.getName());

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
