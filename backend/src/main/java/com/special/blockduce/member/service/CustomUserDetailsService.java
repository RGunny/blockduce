package com.special.blockduce.member.service;

import com.special.blockduce.member.domain.SecurityMember;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByEmail(email);
        if(member == null) {
            throw new UsernameNotFoundException(email +"사용자가 존재하지 않습니다.");
        }
        return (UserDetails) new SecurityMember(member);
    }
}
