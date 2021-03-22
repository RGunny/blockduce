package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Member findMemberByname(String username);
    Member findUserByEmail(String email);
}
