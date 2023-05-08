package org.study.jpa.space.entitymapping;

import org.study.jpa.entity.Item;
import org.study.jpa.entity.Order;
import org.study.jpa.entity.Member;
import org.study.jpa.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class RelationMappingMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setName("테스터");
        em.persist(member);

        Item item1 = new Item();
        item1.setName("아이템1");
        Item item2 = new Item();
        item2.setName("아이템2");
        em.persist(item1);
        em.persist(item2);

        Order order = new Order();
        order.setMember(member);
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
