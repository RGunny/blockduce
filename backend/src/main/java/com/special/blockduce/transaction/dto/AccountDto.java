package com.special.blockduce.transaction.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountDto {

    private String account;
    private Double eth;
    private Double dbc;
    private String key;

}
