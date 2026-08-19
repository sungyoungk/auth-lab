package com.sungyoung.authlab.member.controller;

import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.service.MemberService;
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

}
