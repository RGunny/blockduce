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
import java.util.Optional;

public interface DbcRepository extends CrudRepository<DBC,Long> {
//    int countById(Long memberId);


    @Query("select sum(d.value) from DBC d" +
            " where d.status = :status "+
            "and d.member.id = :memberId"
    )
    Double countTotalSendDbcById(Long memberId,DBCStatus status);

    @Query("select sum(d.value) from DBC d" +
            " where d.member.id = :memberId "+
            "and d.status = 'REWARD'"
    )
    Double countTotalReceiveDbcById(Long memberId);

    // 관리자 1번이 준 횟수
    @Query("select count(d.value) from DBC d" +
            " where d.member.id = :memberId "+
            "and d.status = 'REWARD'"
    )
    Integer receiveDbcTransactionsById(Long memberId);

    @Query("select count(d.value) from DBC d" +
            " where d.status = :status "+
            "and d.member.id = :memberId"
    )
    Integer countByMember(Long memberId, DBCStatus status);

    @Query("select d from DBC d join fetch d.member c" +
            " where c.id = :receiverId" +
            " and d.status = :status" +
            " and month(d.localDateTime) = :month and day(d.localDateTime) = :day")
    Optional<DBC> isRewarded(Long receiverId, DBCStatus status, int month, int day);


//    int countBySenderId(Long memberId);
}