package com.special.blockduce.candidate.dto;

import com.special.blockduce.vote.domain.Vote;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReadCandidatesResponse {

    private Long candidateId;

    private String candidateName;

    private int age;

    private String agency;

    private String candidateImg;

    public ReadCandidatesResponse(Long candidateId, String candidateName, int age, String agency, String candidateImg) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.age = age;
        this.agency = agency;
        this.candidateImg = candidateImg;
    }
}
