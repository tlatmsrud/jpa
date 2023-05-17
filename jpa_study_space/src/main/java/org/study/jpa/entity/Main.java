package org.study.jpa.entity;

import org.study.jpa.entity.item.Book;
import org.study.jpa.entity.item.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        addOrderAndDelivery(em);

        tx.commit();

        em.close();
        emf.close();


    }

    public static void addOrderAndDelivery(EntityManager em){

        Delivery delivery = new Delivery();
        delivery.setCity("시티");
        delivery.setStreet("거리");
        delivery.setZipcode("주소");
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(1000);

        Order order = new Order();
        order.setDelivery(delivery);
        order.addOrderItem(orderItem);
        em.persist(order);
    }

}
