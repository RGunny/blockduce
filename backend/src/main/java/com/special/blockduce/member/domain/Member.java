package com.special.blockduce.member.domain;

import com.special.blockduce.config.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @Builder
    public Member(@NotBlank @Email String email, @NotBlank String name, String img, Long point, Long coin) {
        this.email = email;
        this.name = name;
        this.img = img;
        this.point = point;
        this.coin = coin;
    }

    public Member(String email, String s, List<GrantedAuthority> authorityList) {
    }
}
