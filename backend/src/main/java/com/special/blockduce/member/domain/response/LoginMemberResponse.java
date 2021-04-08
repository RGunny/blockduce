package com.special.blockduce.member.domain.response;

import com.special.blockduce.member.domain.Member;
import lombok.Getter;

@Getter
public class LoginMemberResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String accessToken;

    public LoginMemberResponse(Long id, String email, String name, String profileImageUrl, String accessToken) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.accessToken = accessToken;
    }

    public static LoginMemberResponse of(Member member, String accessToken) {
        return new LoginMemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getImg(),
                accessToken
        );
    }
}
