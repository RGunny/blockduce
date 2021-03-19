package com.special.blockduce.member.controller;

import com.special.blockduce.Response;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("signup")
    public Response signUpMember(@RequestBody Member member){
        Response response = new Response();
        try{
            authService.signUpMember(member);
            response.setReponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
        }catch (Exception e){
            response.setReponse("faild");
            response.setMessage("회원가입을 실패하였습니다.");
            response.setData(e.toString());
        }
        return response;
    }
}
