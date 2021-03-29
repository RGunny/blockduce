package com.special.blockduce.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberForm {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String img;
    private String jwt;
    private String kid;
    private String account;
    private Double eth;
    private Double dbc;
    private Boolean ismem;
    private String nickname;
    private String intro;
    private String wallet;
    private String key;


    @Builder
    public MemberForm(Long id,String email,String password,String name,String img,String jwt,
                      String kid, String account,Double eth,Double dbc,Boolean ismem,String nickname,
                      String intro,String wallet,String key) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.img = img;
        this.jwt = jwt;
        this.kid = kid;
        this.account = account;
        this.eth = eth;
        this.dbc = dbc;
        this.ismem = ismem;
        this.nickname = nickname;
        this.intro = intro;
        this.wallet = wallet;
        this.key = key;
    }
}
