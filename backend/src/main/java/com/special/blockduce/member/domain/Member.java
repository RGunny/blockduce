package com.special.blockduce.member.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // pk

    //    @NotEmpty
    private String email;

    @Column(name = "member_name")
    private String name;

    private String password;

}
