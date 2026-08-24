package com.sungyoung.authlab.member.service;

import com.sungyoung.authlab.auth.dto.*;
import com.sungyoung.authlab.auth.jwt.JwtProvider;
import com.sungyoung.authlab.auth.mapper.RefreshTokenMapper;
import com.sungyoung.authlab.common.config.PasswordEncoderConfig;
import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final RefreshTokenMapper refreshTokenMapper;
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

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = createAndSaveRefreshToken(member.getId());
        return new LoginResponse(accessToken, refreshToken);
    }

    private String createAndSaveRefreshToken(Long memberId) {
        String token = UUID.randomUUID().toString();

        RefreshTokenDto dto = new RefreshTokenDto();
        dto.setMemberId(memberId);
        dto.setToken(token);
        dto.setExpiresAt(
                LocalDateTime.now().plusSeconds(jwtProvider.getRefreshExpirationMs() / 1000)
        );

        refreshTokenMapper.insert(dto);
        return token;
    }

    public LoginResponse refresh(RefreshRequest request) {
        RefreshTokenDto saved = refreshTokenMapper.findByToken(request.getRefreshToken());
        if(saved == null || saved.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("유효하지 않은 refresh token 입니다");
        }

        MemberDto member = memberMapper.findById(saved.getMemberId());
        if(member == null) {
            throw new IllegalArgumentException("유효하지 않은 refresh token 입니다");
        }

        // 로테이션: 예전 refresh token 삭제 후 새로 발급
        refreshTokenMapper.deleteByToken(request.getRefreshToken());

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = createAndSaveRefreshToken(member.getId());

        return new LoginResponse(accessToken, refreshToken);
    }

    public void logout(RefreshRequest request) {
        refreshTokenMapper.deleteByToken(request.getRefreshToken());
    }


}
