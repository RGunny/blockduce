package com.special.blockduce.member.service;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.request.LoginMemberRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    @Autowired AuthService authService;

    @Test
    void signUpMember() {
        Member member = new Member();
        member.setName("박상우");
        member.setPassword("a1234");
        member.setEmail("403.blockduce@kakao.com");
        try {
            authService.signUpMember(member);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void loginMember() {
        LoginMemberRequest loginMember = new LoginMemberRequest("403.blockduce@kakao.com","a1234");
        try {
            authService.loginMember(loginMember.getUserEmail(),loginMember.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}