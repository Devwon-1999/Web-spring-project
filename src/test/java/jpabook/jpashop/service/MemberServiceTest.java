package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //Junit 실행시 Spring과 실행하고 싶다면 넣는 코드
@SpringBootTest // 스프링 부트를 띄운 상태에서 실행할라면 필요한 어노테이션 이 어노테이션이 없다면 @Autowired들도 모두 실패
@Transactional // 테스트 중 데이터베이스에 데이터가 입력되었던 것을 Rollback한다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false) //이게 없다면 쿼리문을 볼 수 없다. 대신 데이터베이스에 반영된다. insert문을 확인할 수 있다.
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        //em.flush(); //테스트 구동시 사용되는 쿼리문 확인. insert문을 확인할 수 있다.
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when1
//        위에 @Test 어노테이션에(expected = IllegalStateException.class)를 넣어주면 아래의 코드를 다 지워도된다.
//        memberService.join(member1);
//        try{
//            memberService.join(member2); //예외 발생
//        }catch (IllegalStateException e){
//            return;
//        }

        //when2
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }
}