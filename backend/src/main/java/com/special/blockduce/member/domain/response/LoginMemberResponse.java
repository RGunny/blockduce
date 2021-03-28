package com.special.blockduce.member.domain.response;

import com.special.blockduce.member.domain.Member;
import lombok.Getter;

@Getter
public class LoginMemberResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private Long point;
    private Long coin;
    private String accessToken;

    public LoginMemberResponse(Long id, String email, String name, String profileImageUrl, Long point, Long coin, String accessToken) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.point = point;
        this.coin = coin;
        this.accessToken = accessToken;
    }

    public static LoginMemberResponse of(Member member, String accessToken) {
        return new LoginMemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getImg(),
                member.getPoint(),
                member.getCoin(),
                accessToken
        );
    }
}
