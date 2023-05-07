package org.study.jpa.space.entitymapping;

import org.study.jpa.main.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GeneratedValueMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Board board = new Board();
        em.persist(board);

        tx.commit();

        em.close();
        emf.close();

    }
}
