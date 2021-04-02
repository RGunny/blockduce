package com.special.blockduce.transaction.domain;
import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.Salt;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DBC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dbc_id")
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
    private String timeStamp;

    @Builder
    public DBC(Long id, String senderAccount,Long senderId, String receiverAccount, String blockHash,Long value,
               Long transactionFee,Long gasUsed,String timeStamp,Long receiverId,Long blockNumber
                  ) {
        this.id = id;
        this.member = Member.builder().
                id(senderId).build();
        this.candidate = Candidate.builder().
                id(receiverId).build();
        this.blockHash = blockHash;
        this.value = value;
        this.transactionFee = transactionFee;
        this.gasUsed = gasUsed;
        this.timeStamp = timeStamp;
        this.blockNumber = blockNumber;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }
}
