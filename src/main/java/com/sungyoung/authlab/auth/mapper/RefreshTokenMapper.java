package com.sungyoung.authlab.auth.mapper;

import com.sungyoung.authlab.auth.dto.RefreshTokenDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {

    void insert(RefreshTokenDto refreshToken);
    RefreshTokenDto findByToken(String token);
    void deleteByToken(String token);
    void deletedByMemberId(Long memberId);

}
