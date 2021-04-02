package com.special.blockduce.transaction.repository;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.transaction.domain.DBC;
import org.springframework.data.repository.CrudRepository;

public interface DbcRepository extends CrudRepository<DBC,Long> {
    int countById(Long memberId);

//    int countBySenderId(Long memberId);
}