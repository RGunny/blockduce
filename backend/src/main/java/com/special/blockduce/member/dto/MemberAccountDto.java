package com.special.blockduce.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberAccountDto {
    private String account;
    private Double eth;
    private Double dbc;
    private String key;

    @Builder
    public MemberAccountDto(String account,Double eth,Double dbc,String key) {

        this.account = account;
        this.eth = eth;
        this.dbc = dbc;
        this.key = key;
    }
}
