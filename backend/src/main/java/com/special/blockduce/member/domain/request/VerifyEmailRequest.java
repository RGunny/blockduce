package com.special.blockduce.member.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VerifyEmailRequest {
    String username;
}