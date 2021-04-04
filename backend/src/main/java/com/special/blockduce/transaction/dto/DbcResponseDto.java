package com.special.blockduce.transaction.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DbcResponseDto {

    private Long blockNumber;
    private String blockHash;

    private String candidateImg;
    private String candidateName;
    private String agency;

    private LocalDateTime localDateTime;
    private LocalDate localDate;

    private Long value; // 투표한 dbc 양
    private int totalValue; // 해당 날의 투표한 dbc 총량

    public DbcResponseDto(Long blockNumber, String blockHash, String candidateImg, String candidateName, String agency, LocalDateTime localDateTime, Long value) {
        this.blockNumber = blockNumber;
        this.blockHash = blockHash;
        this.candidateImg = candidateImg;
        this.candidateName = candidateName;
        this.agency = agency;
        this.localDateTime = localDateTime;
        this.value = value;
    }
}
