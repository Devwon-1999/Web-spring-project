package jpabook.jpashop.controller;

import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createFrom(Model model){
        // 컨트롤러에서 view로 넘어갈때 데이터를 실어서 넘긴다
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
}
