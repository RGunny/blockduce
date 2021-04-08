package com.special.blockduce.transaction.dto;

import com.special.blockduce.transaction.domain.DBCStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class DbcEthDto {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String senderAccount;
    private String receiverAccount;
    private String blockHash;
    private Long blockNumber;
    private Long value;
    private Long transactionFee;
    private Long gasUsed;
    private LocalDateTime localDateTime;
    private Long isDbcEth;
    private String transactionHash;
    private DBCStatus status;

    @Builder
    public DbcEthDto(Long id,Long senderId,Long receiverId,String blockHash,Long value,
                      Long transactionFee, Long gasUsed,LocalDateTime localDateTime,String senderAccount,
                  String receiverAccount,Long isDbcEth,Long blockNumber,String transactionHash, DBCStatus status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.blockHash = blockHash;
        this.value = value;
        this.transactionFee = transactionFee;
        this.gasUsed = gasUsed;
        this.localDateTime = localDateTime;
        this.isDbcEth = isDbcEth;
        this.blockNumber = blockNumber;
        this.transactionHash = transactionHash;
        this.status = status;
    }
}
