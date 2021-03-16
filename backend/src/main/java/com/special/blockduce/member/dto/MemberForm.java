package com.special.blockduce.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    private String email;
    private String password;
    private String memberName;
}
