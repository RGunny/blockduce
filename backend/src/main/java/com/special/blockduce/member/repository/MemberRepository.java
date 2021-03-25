package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.dto.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Optional<Member> findMemberByName(String username);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findOptionalByKid(String id);
    boolean existsByEmail(String email);
}
