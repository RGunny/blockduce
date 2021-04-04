package com.special.blockduce.transaction.dto;

import lombok.Data;

@Data
public class CandidateDto {

    private String candidateImg;
    private String candidateName;
    private String agency;

    public CandidateDto() {
    }

    public CandidateDto(String candidateImg, String candidateName, String agency) {
        this.candidateImg = candidateImg;
        this.candidateName = candidateName;
        this.agency = agency;
    }
}
