package com.special.blockduce.member.service;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.Salt;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.utils.SaltUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private MemberRepository memberRepository;

    public AuthService (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void signUpMember(Member member){
        String password = member.getPassword();
        String salt = SaltUtil.genSalt();
        member.setSalt(new Salt(salt));
        member.setPassword(SaltUtil.encodePassword(salt,password));
        memberRepository.save(member);
    }

    public Member loginMember(String id, String password)throws Exception{
        Member memeber = memberRepository.findMemberByname(id);
        if(memeber == null) throw new Exception("멤버가 조회 되지 않습니다.");
        String salt = memeber.getSalt().getSalt();
        password = SaltUtil.encodePassword(salt,password);
        if(!memeber.getPassword().equals(password)){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return memeber;
    }

}
