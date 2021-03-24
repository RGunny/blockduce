package com.special.blockduce.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileDto {
    private String id;
    private String email;
    private String name;
    private String img;

    public ProfileDto(String id, String email, String name, String img) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.img = img;
    }
}
