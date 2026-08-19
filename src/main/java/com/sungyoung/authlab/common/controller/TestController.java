package com.sungyoung.authlab.common.controller;

import com.sungyoung.authlab.common.service.TestService;
import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final MemberMapper memberMapper;

    @GetMapping("/api/hello")
    public String hello() {
        return testService.sayHello();
    }

    @GetMapping("/api/test/member/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberMapper.findById(id);
    }
}
