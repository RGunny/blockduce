package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.dto.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findOptionalByKid(String id);
    Member findMemberByname(String username);
    Member findUserByEmail(String email);
}
