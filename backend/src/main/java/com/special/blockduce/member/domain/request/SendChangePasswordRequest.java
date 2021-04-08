package com.special.blockduce.member.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendChangePasswordRequest {
    private String name;
    private String email;
}
