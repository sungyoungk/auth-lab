package com.sungyoung.authlab.member.controller;

import com.sungyoung.authlab.auth.dto.ChangePasswordRequest;
import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public MemberDto me() {
//        Long memberId = (Long)request.getAttribute("memberId");
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Long memberId = (Long) authentication.getPrincipal();
        return memberService.findById(memberId);
    }

    @PostMapping("/api/members/me/password")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest body) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Long memberId = (Long) authentication.getPrincipal();

//        Long memberId = (Long)request.getAttribute("memberId");
        memberService.changePassword(memberId, body);
    }

}
