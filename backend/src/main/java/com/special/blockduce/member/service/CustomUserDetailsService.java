package com.special.blockduce.member.service;

import com.special.blockduce.exceptions.EntityNotFoundException;
import com.special.blockduce.exceptions.ErrorCode;
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
    private MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository){this.memberRepository = memberRepository;}

    public Member findMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email).orElseThrow(()-> new EntityNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = findMemberByEmail(email);
        if(member == null) {
            throw new UsernameNotFoundException(email +"사용자가 존재하지 않습니다.");
        }
        return (UserDetails) new SecurityMember(member);
    }
}
