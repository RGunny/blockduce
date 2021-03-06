package com.special.blockduce.transaction.domain;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ETH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eth_id")
    private Long id; // pk

    // ETH_tran와 Member는 다대일 관계, 주인    (FORM), (MEMBER_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member member;

    // ETH_tran와 Candidate는 다대일 관계, 주인    (FORM), (MEMBER_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Candidate candidate;

    @Column(name = "sender_account")
    private String senderAccount;

    @Column(name = "transaction_hash")
    private String transactionHash;

    @Column(name = "receiver_account")
    private String receiverAccount;

    @Column(name = "block_hash")
    private String blockHash;

    @Column(name = "block_number")
    private Long blockNumber;

    @Column(name = "value")
    private Long value;

    @Column(name = "transaction_fee")
    private Long transactionFee;

    @Column(name = "gas_used")
    private Long gasUsed;

    @Column(name = "time_stamp")
    private LocalDateTime localDateTime;

    @Builder
    public ETH(Long id, String senderAccount, Long senderId, String receiverAccount, String blockHash, Long value,
               Long transactionFee, Long gasUsed, LocalDateTime localDateTime, Long receiverId, Long blockNumber,
               String transactionHash) {
        this.id = id;
        this.member = Member.builder().
                id(senderId).build();
        this.candidate = Candidate.builder().
                id(receiverId).build();
        this.blockHash = blockHash;
        this.value = value;
        this.transactionFee = transactionFee;
        this.gasUsed = gasUsed;
        this.localDateTime = localDateTime;
        this.blockNumber = blockNumber;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.transactionHash = transactionHash;
    }

    public ETH updateRewardEth(ETH eth, Long receiverId, Long senderId) {

        eth.setMember(Member.builder().id(receiverId).build());
        eth.setCandidate(Candidate.builder().id(senderId).build());

        return eth;
    }
}
