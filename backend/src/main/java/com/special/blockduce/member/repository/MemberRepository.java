package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Optional<Member> findMemberByName(String username);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByid(Long id);
    Optional<Member> findOptionalByKid(String id);
    boolean existsByEmail(String email);

    Optional<Member> findOptionalById(Long memberId);

    Optional<Member> findOptionalByEmail(String email);
}
