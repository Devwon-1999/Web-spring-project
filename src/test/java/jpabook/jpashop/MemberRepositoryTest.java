//package jpabook.jpashop;
//
//
//import jpabook.jpashop.domain.Member;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MemberRepositoryTest {
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    public void testMember() throws Exception{
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        //then
//        Assertions.assertEquals(findMember.getId(), member.getId());
//        Assertions.assertEquals(findMember.getUsername(), member.getUsername());
//        // 같은 영속성 안에서는 id값이 같다면 같은 entity로 분류한다.
//        Assertions.assertEquals(findMember, member);
//        System.out.println("findMember == member: " + (findMember == member));
//    }
//}