package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Account;
import com.special.blockduce.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Long> {

}