package org.study.jpa.space.querydsl;


import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.impl.JPAQuery;
import org.study.jpa.entity.Member;
import org.study.jpa.entity.QMember;
import org.study.jpa.entity.QOrder;
import org.study.jpa.entity.QOrderItem;
import org.study.jpa.entity.item.Item;
import org.study.jpa.entity.item.QItem;
import org.study.jpa.main.QTestMember;
import org.study.jpa.main.TestMember;

import javax.naming.directory.SearchResult;
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
            join(em);
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void login1(EntityManager em){
        JPAQuery<Member> query = new JPAQuery(em);

        QMember qMember = new QMember("m");

        List<Member> qMemberList =
                query.from(qMember)
                .where(qMember.name.eq("회원"))
                .orderBy(qMember.name.desc()).fetch();

    }

    public static void login2(EntityManager em){
        JPAQuery<Item> query = new JPAQuery(em);

        QItem item = QItem.item;

        List<Item> list = query.from(item)
                .where(item.name.eq("테스트").and(item.price.gt(20000))
                        .and(item.name.contains("상"))).fetch();

    }

    public static void paging(EntityManager em){
        JPAQuery<Member> query = new JPAQuery(em);

        QMember qMember = new QMember("m");

        QueryModifiers queryModifiers = new QueryModifiers(10L,1L);
        List<Member> qMemberList =
                query.from(qMember)
                        .orderBy(qMember.name.desc())
                        .restrict(queryModifiers)
                        .fetch();
    }


    public static void join(EntityManager em){
        JPAQuery query = new JPAQuery(em);

        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QOrderItem orderItem = QOrderItem.orderItem;

        query.from(order)
                .join(order.member, member)
                .leftJoin(order.orderItemList, orderItem)
                .fetch();
        //      left outer join
        //            order_item orderiteml2_
        //                on order0_.order_id=orderiteml2_.order_id

        query.from(order)
                .leftJoin(order.orderItemList, orderItem)
                .on(orderItem.count.gt(2))
                .fetch();
    }

}
