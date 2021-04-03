package com.special.blockduce.member.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMemberRequest {
    private String email;
    private String password;

    public LoginMemberRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
