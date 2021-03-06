package com.special.blockduce.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(400,"AU_001", "해당 유저를 찾을 수 없습니다."),
    EMAIL_NOT_FOUND(400,"AU_002", "해당 이메일을 찾을 수 없습니다.");


    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
