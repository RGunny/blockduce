package com.special.blockduce.transaction.repository;

import com.special.blockduce.member.domain.Account;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.transaction.domain.DBC;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface DbcRepository extends CrudRepository<DBC,Long> {
//    int countById(Long memberId);

    int countByMember(Member member);

    @Query("select sum(d.value) from DBC d where d.member.id = :memberId")
    Double countTotalSendDbcById(Long memberId);

    @Query("select sum(d.value) from DBC d where d.candidate.id = :memberId")
    Double countTotalReceiveDbcById(Long memberId);


    // 주는 사람(member)의 account 는
    @Query("select sum(d.value) from DBC d" +
            " where d.candidate.id = 20"+
            "and d.member.id = :memberId"
    )
    Integer receiveDbcTransactionsById(Long memberId);


//    int countBySenderId(Long memberId);
}