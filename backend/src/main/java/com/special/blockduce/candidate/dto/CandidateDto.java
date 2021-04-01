package com.special.blockduce.candidate.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
public class CandidateDto {

    private Long id;
    private String name;
    private int age;
    private String agency;
    private String img;
    private String key;
    private String account;
    private Double dbc;
    private String intro;

    @Builder
    public CandidateDto(Long id,String name,int age,String agency,String img,
                        String key,String account,Double dbc,String intro) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.agency = agency;
        this.img = img;
        this.key = key;
        this.account = account;
        this.dbc = dbc;
        this.intro = intro;
        this.dbc = dbc;
    }
}
