package com.special.blockduce.member.domain;

import com.special.blockduce.config.UserRole;
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

    @Column(name = "private_key")
    private String key;

    @Column(name = "member_account")
    private String account;

    @Column(name = "member_eth")
    private Double eth;

    @Column(name = "member_dbc")
    private Double dbc;

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

    @Column(name = "member_point")
    private Long point;

    @Column(name = "member_coin")
    private Long coin;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;


    public void updateEth(Double eth) {
        this.eth = eth;
    }

    public void updateDbc(Double dbc) {
        this.dbc = dbc;
    }

    @Builder
    public Member(Long id, @NotBlank String password, @NotBlank @Email String email, @NotBlank String name,
                  Long point, Long coin, UserRole role, Salt salt,@NotBlank String kid,String account,
                  @NotBlank Boolean ismem,@NotBlank String intro,@NotBlank String nickname,
                  @NotBlank String img, String key,Double eth,Double dbc) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.point = point;
        this.coin = coin;
        this.role = role;
        this.salt = salt;
        this.kid = kid;
        this.ismem = ismem;
        this.intro = intro;
        this.nickname = nickname;
        this.img = img;
        this.account = account;
        this.key=key;
        this.eth=eth;
        this.dbc=dbc;
    }
}
