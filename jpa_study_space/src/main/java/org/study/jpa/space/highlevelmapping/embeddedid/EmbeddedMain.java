package org.study.jpa.space.highlevelmapping.embeddedid;

import org.study.jpa.space.highlevelmapping.idclass.ICParent;
import org.study.jpa.space.highlevelmapping.idclass.ICParentId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbeddedMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            EBParentId id = new EBParentId("myId1", "myId2");

            EBParent parent = new EBParent();
            parent.setId(id);
            parent.setName("parentName");
            em.persist(parent);

            em.flush();
            em.clear();

            EBParentId parentId = new EBParentId("myId1","myId2");
            EBParent findParent = em.find(EBParent.class, parentId);
            System.out.println(findParent.getName());

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
