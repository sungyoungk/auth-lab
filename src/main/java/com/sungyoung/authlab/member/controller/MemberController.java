package com.sungyoung.authlab.member.controller;

import com.sungyoung.authlab.auth.dto.ChangePasswordRequest;
import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberService.findById(id);
    }


    @GetMapping("/api/members/me")
    public MemberDto me(HttpServletRequest request) {
        Long memberId = (Long)request.getAttribute("memberId");
        return memberService.findById(memberId);
    }

    @PostMapping("/api/members/me/password")
    public void changePassword(
            HttpServletRequest request,
            @Valid @RequestBody ChangePasswordRequest body
            ) {
        Long memberId = (Long)request.getAttribute("memberId");
        memberService.changePassword(memberId, body);
    }

}
