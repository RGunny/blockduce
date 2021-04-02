package com.special.blockduce.candidate.domain;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.domain.Salt;
import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.ETH;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    // candidate와 ETH는 일대다 관계
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ETH> ethTransactions = new ArrayList<>();

    // candidate와 DBC는 일대다 관계
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DBC> dbcTransactions = new ArrayList<>();

    @Column(name = "candidate_name")
    private String name;

    private int age;

    private String agency;

    @Column(name = "candidate_img")
    private String img;

    @Column(name = "private_key")
    private String key;

    @Column(name = "candidate_account")
    private String account;

    @Column(name = "candidate_dbc")
    private Double dbc;

    @Column(name = "intro")
    private String intro;

    public void updateDbc(Double dbc) {
        this.dbc = dbc;
    }

    @Builder
    public Candidate(Long id, String name, int age, String agency,
                  String img, String key, String account, Double dbc,
                  String intro) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.agency = agency;
        this.img = img;
        this.key = key;
        this.account = account;
        this.dbc = dbc;
        this.intro = intro;
    }
}
