package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //component scan의 대상이 되어 스프링빈으로 자동 등록
@RequiredArgsConstructor
public class MemberRepository {

    // jpa의 entity 매니저를 주입해줌
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);// persist를 한다고 해서 db에 insert문 안나감

    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    
    public List<Member> findAll(){
        //sql -> from이 테이블 대상으로 query, jpql -> entity 객체에 대한 query
        return em.createQuery("select m from Member m ", Member.class) //jpql -> sql이랑 다름
                .getResultList();
    }

    public List<Member> findByName(String name){
        List<Member> memberList = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return memberList;
    }
}
