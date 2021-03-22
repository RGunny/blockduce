package com.special.blockduce.member.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
	private String email;
	private String password;
}
