package com.special.blockduce.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberForm {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String img;
    private String jwt;
    private String kid;


    public MemberForm(String kid) {
        this.kid = kid;

    }
    public MemberForm( String email, String name) {
        this.email = email;
        this.name = name;
    }

    public MemberForm( String kid,String email, String name) {
        this.kid = kid;
        this.email = email;
        this.name = name;
    }

    public MemberForm(String kid, String email, String name, String password) {
        this.kid = kid;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
