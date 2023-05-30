package org.study.jpa.space.nativeSql;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.study.jpa.entity.Member;
import org.study.jpa.entity.QMember;
import org.study.jpa.entity.QOrder;
import org.study.jpa.entity.QOrderItem;
import org.study.jpa.entity.item.Item;
import org.study.jpa.entity.item.QItem;
import org.study.jpa.space.proxy.Student;
import org.study.jpa.space.querydsl.MemberDto;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            resultMapping(em);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void logic(EntityManager em){

        String sql = "SELECT * FROM MEMBER WHERE NAME LIKE '%' || ? || '%'";
        Query query = em.createNativeQuery(sql,Member.class)
                .setParameter(1,"H");

        List<Member> resultList = query.getResultList();

        for(Member member : resultList){
            System.out.println(member.getName());
            member.setName("바꿔조");
        }
        em.flush();
    }

    public static void selectValue(EntityManager em){
        String sql = "SELECT NAME, MEMBER_ID FROM MEMBER";
        Query nativeQuery = em.createNativeQuery(sql);

        List<Object[]> resultList = nativeQuery.getResultList();
        for(Object[] row : resultList){
            System.out.println("name = "+row[0]);
            System.out.println("id = "+row[1]);
        }
    }

    public static void resultMapping(EntityManager em){
        String sql = "SELECT S.STUDENT_ID, S.NAME, S.CLASS_ID, C.NAME AS ClassroomName FROM STUDENT S INNER JOIN CLASSROOM C ON S.CLASS_ID = C.CLASSROOM_ID";

        Query nativeQuery = em.createNativeQuery(sql, "nameWithClassroomName");

        List<Object[]> resultList = nativeQuery.getResultList();

        for(Object[] row : resultList){
            Student student = (Student)row[0];
            String className = (String)row[1];
            System.out.println(student.getName());
            System.out.println(className);
        }
    }


}
