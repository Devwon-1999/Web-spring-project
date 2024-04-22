package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //롬복의 어노테이션
public class MemberService {

    private final MemberRepository memberRepository; //final을 해놓으면 컴파일 시점에 체크를 할 수 있다
    //@Autowired //생성자가 1개인 경우 자동으로 스프링에서 Autowired해준다
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    } @RequiredArgsConstructor이 있다면 이 코드는 없어도된다.

    //회원 가입
    @Transactional //이부분에 다시 줘서 이부분만 readOnly가 true가 아니게 한다.
    public Long join(Member member){
        validateDuplicateMember(member); //중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {//중복 검증
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    //@Transactional(readOnly = true) // readOnly = true -> 조회 성능 최적화 읽기 전용
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true) //위에서 한번만쓰고
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
