package com.special.blockduce.member.domain;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.dto.MemberAccountDto;
import com.special.blockduce.member.dto.MemberForm;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id; // pk

    @Column(name = "member_account")
    private String account;

    @Column(name = "member_eth")  //candidate는 eth 없음
    private Double eth;

    @Column(name = "member_dbc")
    private Double dbc;

    @Column(name = "private_key")
    private String key;

    public void updateEth(Double eth) {
        this.eth = eth;
    }

    public void updateDbc(Double dbc) {
        this.dbc = dbc;
    }

    @Builder
    public Account(String key, String account, Double dbc, Double eth) {
        this.key = key;
        this.account =account;
        this.dbc = dbc;
        this.eth = eth;
    }
}
