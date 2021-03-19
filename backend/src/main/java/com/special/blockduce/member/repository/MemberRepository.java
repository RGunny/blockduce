package com.special.blockduce.member.repository;

import com.special.blockduce.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Member findMemberByname(String username);
}
