package com.sungyoung.authlab.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String loginId;
    private String name;
    private String role;

}
