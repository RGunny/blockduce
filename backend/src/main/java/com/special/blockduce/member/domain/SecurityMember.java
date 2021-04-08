package com.special.blockduce.member.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {
    private static final long serialVersionUiD = 1L;

    public SecurityMember(Member member){
        super(member.getEmail(),"{noop}"+ member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
    }
}
