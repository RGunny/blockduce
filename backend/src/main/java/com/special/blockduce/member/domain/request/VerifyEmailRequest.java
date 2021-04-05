package com.special.blockduce.member.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VerifyEmailRequest {
    String email;

    public VerifyEmailRequest(String email) {
        this.email = email;
    }
}