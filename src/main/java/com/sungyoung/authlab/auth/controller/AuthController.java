package com.sungyoung.authlab.auth.controller;

import com.sungyoung.authlab.auth.dto.LoginRequest;
import com.sungyoung.authlab.auth.dto.LoginResponse;
import com.sungyoung.authlab.auth.dto.RefreshRequest;
import com.sungyoung.authlab.auth.dto.SignupRequest;
import com.sungyoung.authlab.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/api/auth/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

    @PostMapping("/api/auth/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return memberService.login(request);
    }
/*
    @PostMapping("/api/auth/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
*/
    @GetMapping("/api/admin/ping") // 인가
    public String ping() {
        return "pong";
    }

    @PostMapping("/api/auth/refresh")
    public LoginResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return memberService.refresh(request);
    }

    @PostMapping("/api/auth/logout")
    public void logout(@Valid @RequestBody RefreshRequest request) {
        memberService.logout(request);
    }

}
