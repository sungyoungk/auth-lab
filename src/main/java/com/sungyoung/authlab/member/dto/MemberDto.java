package com.sungyoung.authlab.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String role;

}
