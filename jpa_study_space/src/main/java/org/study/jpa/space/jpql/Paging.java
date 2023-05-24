package org.study.jpa.space.jpql;

import org.study.jpa.entity.Member;
import org.study.jpa.space.proxy.Classroom;
import org.study.jpa.space.proxy.Student;

import javax.persistence.*;
import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-05-24
 * description  :
 */
public class Paging {
    public static void main(String[] args) {

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study_space");
            EntityManager em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();

            tx.begin();
            insert(em);
            TypedQuery<Member> query = em.createQuery(
                    "select m from Member m order by m.name desc", Member.class);

            query.setFirstResult(0); // 몇번째 부터 시작할것인가?
            query.setMaxResults(2); // 조회할 데이터는 몇개인가?
            List<Member> list = query.getResultList();
            for(Member member : list){
                System.out.println(member.getName());
            }
            tx.commit();

            em.close();
            emf.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void insert(EntityManager em){
        Member member1 = new Member();
        member1.setName("심승경");

        Member member2 = new Member();
        member2.setName("박승경");

        Member member3 = new Member();
        member3.setName("헹승경");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();
    }
}
