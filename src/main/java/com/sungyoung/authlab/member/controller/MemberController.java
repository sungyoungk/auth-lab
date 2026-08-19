package com.sungyoung.authlab.member.controller;

import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberService.findById(id);
    }


    @GetMapping("/api/members/me")
    public MemberDto me(HttpSession session) {
        Long memberId = (Long)session.getAttribute("memberId");
        return memberService.findById(memberId);
    }

}
