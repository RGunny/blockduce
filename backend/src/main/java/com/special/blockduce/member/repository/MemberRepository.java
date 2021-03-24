package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Optional<Member> findMemberByName(String username);
    Member findMemberByEmail(String email);
    boolean existsByEmail(String email);
}
