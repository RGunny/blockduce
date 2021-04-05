package com.special.blockduce.transaction.repository;

import com.special.blockduce.member.domain.Account;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.DBCStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface DbcRepository extends CrudRepository<DBC,Long> {
//    int countById(Long memberId);


    @Query("select sum(d.value) from DBC d" +
            " where d.status = :status "+
            "and d.member.id = :memberId"
    )
    Double countTotalSendDbcById(Long memberId,DBCStatus status);

    @Query("select sum(d.value) from DBC d" +
            " where d.candidate.id = 1"+
            "and d.member.id = :memberId"
    )
    Double countTotalReceiveDbcById(Long memberId);

    // 관리자 1번이 준 횟수
    @Query("select count(d.value) from DBC d" +
            " where d.candidate.id = 1"+
            "and d.member.id = :memberId"
    )
    Integer receiveDbcTransactionsById(Long memberId);

    @Query("select count(d.value) from DBC d" +
            " where d.status = :status "+
            "and d.member.id = :memberId"
    )
    Integer countByMember(Long memberId, DBCStatus status);


//    int countBySenderId(Long memberId);
}