package com.special.blockduce.member.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMemberRequest {
    private String userEmail;
    private String password;

    public LoginMemberRequest(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
