package com.special.blockduce.transaction.repository;

import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.ETH;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EthRepository extends CrudRepository<ETH,Long> {


    @Query("select count(e.value) from ETH e" +
            " where e.candidate.id = 1"+
            "and e.member.id = :memberId"
    )
    Integer receiveEthTransactionsById(Long memberId);

    @Query("select sum(e.value) from ETH e" +
            " where e.candidate.id = 1"+
            "and e.member.id = :memberId"
    )
    Double countTotalReceiveEthById(Long memberId);

}
