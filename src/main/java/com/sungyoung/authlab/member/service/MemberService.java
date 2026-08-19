package com.sungyoung.authlab.member.service;

import com.sungyoung.authlab.member.dto.MemberDto;
import com.sungyoung.authlab.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberDto findById(Long id) {
        return memberMapper.findById(id);
    }
}
