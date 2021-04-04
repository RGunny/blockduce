package com.special.blockduce.member.domain;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.dto.MemberAccountDto;
import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.ETH;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // pk

    // Member와 ETH는 일대다 관계
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ETH> ethTransactions = new ArrayList<>();

    // Member와 DBC는 일대다 관계
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DBC> dbcTransactions = new ArrayList<>();

    @Column(name = "kakao_id")
    private String kid;


    @Column(name = "is_ourmember")
    private Boolean ismem;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(name = "member_name")
    private String name;

    //    @ColumnDefault("images/default-image.png")
    @Column(name = "member_img")
    private String img;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "intro")
    private String intro;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;


    @Builder
    public Member(Long id, @NotBlank String password, @NotBlank @Email String email, @NotBlank String name,
                   UserRole role, Salt salt,@NotBlank String kid,
                  @NotBlank Boolean ismem,@NotBlank String intro,@NotBlank String nickname,
                  @NotBlank String img,String key1,String account1, Double dbc1,Double eth1) {

        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        this.salt = salt;
        this.kid = kid;
        this.ismem = ismem;
        this.intro = intro;
        this.nickname = nickname;
        this.img = img;
        this.account = Account.builder().
                key(key1).
                account(account1).
                dbc(dbc1).
                eth(eth1).
                build();
    }
}
