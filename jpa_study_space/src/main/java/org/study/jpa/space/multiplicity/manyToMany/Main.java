package org.study.jpa.space.multiplicity.manyToMany;

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

        emf.close();
    }

    public static void logic(EntityManager em){

        MPMember member = new MPMember();
        member.setId("user1");
        member.setUsername("유저1");
        em.persist(member);

        MPProduct product = new MPProduct();
        product.setId("product1");
        product.setName("제품1");
        em.persist(product);

        MPMemberProduct memberProduct = new MPMemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(product);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);

    }
}
