package com.sungyoung.authlab.member.mapper;

import com.sungyoung.authlab.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto findById(Long id);

    MemberDto findByLoginId(String loginId);

    int insert(MemberDto memberDto);


}
