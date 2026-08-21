package com.sungyoung.authlab.member.service;

import com.sungyoung.authlab.auth.dto.LoginRequest;
import com.sungyoung.authlab.auth.dto.LoginResponse;
import com.sungyoung.authlab.auth.dto.SignupRequest;
import com.sungyoung.authlab.auth.jwt.JwtProvider;
import com.sungyoung.authlab.common.config.PasswordEncoderConfig;
import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public MemberDto findById(Long id) {
        return memberMapper.findById(id);
    }

    public void signup(SignupRequest request) {
        MemberDto existing = memberMapper.findByLoginId(request.getLoginId());
        if(existing != null) {
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }

        MemberDto member = new MemberDto();
        member.setLoginId(request.getLoginId());
        member.setName(request.getName());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setRole("USER");

        memberMapper.insert(member);
    }

    public LoginResponse login(LoginRequest request) {
        MemberDto member = memberMapper.findByLoginId(request.getLoginId());
        if(member == null) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다");
        }

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다");
        }

        String token = jwtProvider.createAccessToken(member.getId(), member.getRole());
        return new LoginResponse(token);
    }
}
