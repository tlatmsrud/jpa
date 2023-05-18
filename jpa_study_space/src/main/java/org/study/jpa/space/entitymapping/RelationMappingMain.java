package org.study.jpa.space.entitymapping;

import org.study.jpa.entity.*;
import org.study.jpa.entity.item.Book;
import org.study.jpa.entity.item.Item;
import org.study.jpa.entity.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RelationMappingMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setName("테스터");
        em.persist(member);

        Item item1 = new Book();
        item1.setName("책1");
        Item item2 = new Movie();
        item2.setName("영화1");
        em.persist(item1);
        em.persist(item2);

        Category category = new Category();
        category.setName("카테고리1");
        em.persist(category);

        CategoryItem categoryItem1 = new CategoryItem();
        categoryItem1.setCategory(category);
        categoryItem1.setItem(item1);
        em.persist(categoryItem1);

        CategoryItem categoryItem2 = new CategoryItem();
        categoryItem2.setCategory(category);
        categoryItem2.setItem(item2);
        em.persist(categoryItem2);

        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(new Address("city","street","zipcode"));
        em.persist(delivery);

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        em.persist(order);

        OrderItem orderItem1 = new OrderItem();
        em.persist(orderItem1);
        orderItem1.setItem(item1);
        orderItem1.setOrder(order);

        OrderItem orderItem2 = new OrderItem();
        em.persist(orderItem2);
        orderItem2.setItem(item2);
        orderItem2.setOrder(order);

        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);
        tx.commit();

        Order findOrder = em.find(Order.class, order.getId());
        System.out.println(findOrder.getId());

        Member findMember = order.getMember();
        System.out.println(findMember.getName());

        em.close();
        emf.close();

    }
}
