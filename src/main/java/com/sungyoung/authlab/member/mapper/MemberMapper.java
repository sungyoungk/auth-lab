package com.sungyoung.authlab.member.mapper;

import com.sungyoung.authlab.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    MemberDto findById(Long id);

    MemberDto findByLoginId(String loginId);

    int insert(MemberDto memberDto);

    String findPasswordById(Long id);

    int updatePassword(@Param("id")Long id, @Param("password")String password);


}
