package com.special.blockduce.transaction.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AccountInfoDto {

    private Integer dbcTransactions = 0;
    private Double totalDbc = 0.0 ;
    private Integer receiveTransactionsDbc = 0;
    private Double receiveDbc =0.0;
    private Integer receivedEthTransactions=0;
    private Double receivedEth=0.0;


    @Builder
    public AccountInfoDto(Integer dbcTransactions, Double totalDbc,Integer receiveTransactionsDbc, Double receiveDbc,
                     Integer receivedEthTransactions, Double receivedEth) {

        this.dbcTransactions = dbcTransactions;
        this.totalDbc = totalDbc;
        this.receiveTransactionsDbc = receiveTransactionsDbc;
        this.receiveDbc = receiveDbc;
        this.receivedEthTransactions = receivedEthTransactions;
        this.receivedEth = receivedEth;
    }

}
