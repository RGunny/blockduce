package com.special.blockduce.member.controller;

import com.special.blockduce.Response;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.request.ChangePasswordRequest;
import com.special.blockduce.member.domain.request.LoginMemberRequest;
import com.special.blockduce.member.domain.request.SendChangePasswordRequest;
import com.special.blockduce.member.domain.request.VerifyEmailRequest;
import com.special.blockduce.member.service.AuthService;
import com.special.blockduce.utils.CookieUtil;
import com.special.blockduce.utils.JwtUtil;
import com.special.blockduce.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;
    private RedisUtil redisUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil, CookieUtil cookieUtil, RedisUtil redisUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
        this.redisUtil = redisUtil;
    }

    @PostMapping("signup")
    public Response signUpMember(@RequestBody Member member) {
        Response response = new Response();
        try {
            authService.signUpMember(member);
            response.setReponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
        } catch (Exception e) {
            response.setReponse("faild");
            response.setMessage("회원가입을 실패하였습니다.");
            response.setData(e.toString());
        }
        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginMemberRequest user,
                          HttpServletRequest req,
                          HttpServletResponse res) {
        try {
            final Member member = authService.loginMember(user.getUserEmail(), user.getPassword());
            final String token = jwtUtil.generateToken(member);
            final String refreshJwt = jwtUtil.generateRefreshToken(member);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, member.getName(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }

    @PostMapping("/verify")
    public Response verify(@RequestBody VerifyEmailRequest verifyEmailRequest, HttpServletRequest req, HttpServletResponse res) {
        Response response;
        try {
            Member member = authService.findMemberByname(verifyEmailRequest.getUsername());
            authService.sendVerificationMail(member);
            response = new Response("success", "성공적으로 인증메일을 보냈습니다.", null);
        } catch (Exception exception) {
            response = new Response("error", "인증메일을 보내는데 문제가 발생했습니다.", exception);
        }
        return response;
    }

    @GetMapping("/verify/{key}")
    public Response getVerify(@PathVariable String key) {
        Response response;
        try {
            authService.verifyEmail(key);
            response = new Response("success", "성공적으로 인증메일을 확인했습니다.", null);

        } catch (Exception e) {
            response = new Response("error", "인증메일을 확인하는데 실패했습니다.", null);
        }
        return response;
    }

    @GetMapping("/password/{key}")
    public Response isPasswordUUIdValidate(@PathVariable String key) {
        Response response;
        try {
            if (authService.isPasswordUuidValidate(key))
                response = new Response("success", "정상적인 접근입니다.", null);
            else
                response = new Response("error", "유효하지 않은 Key값입니다.", null);
        } catch (Exception e) {
            response = new Response("error", "유효하지 않은 key값입니다.", null);
        }
        return response;
    }

    @PostMapping("/password")
    public Response requestChangePassword(@RequestBody SendChangePasswordRequest sendChangePasswordRequest) {
        Response response;
        try {
            Member member = authService.findMemberByname(sendChangePasswordRequest.getName());
            if (!member.getEmail().equals(sendChangePasswordRequest.getEmail())) throw new NoSuchFieldException("");
            authService.requestChangePassword(member);
            response = new Response("success", "성공적으로 사용자의 비밀번호 변경요청을 수행했습니다.", null);
        } catch (NoSuchFieldException e) {
            response = new Response("error", "사용자 정보를 조회할 수 없습니다.", null);
        } catch (Exception e) {
            response = new Response("error", "비밀번호 변경 요청을 할 수 없습니다.", null);
        }
        return response;
    }

    @PutMapping("/password")
    public Response changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Response response;
        try {
            Member member = authService.findMemberByname(changePasswordRequest.getEmail());
            authService.changePassword(member, changePasswordRequest.getPassword());
            response = new Response("success", "성공적으로 사용자의 비밀번호를 변경했습니다.", null);
        } catch (Exception e) {
            response = new Response("error", "사용자의 비밀번호를 변경할 수 없었습니다.", null);
        }
        return response;
    }

    @GetMapping("/logout")
    @ApiOperation(value = "로그아웃을 진행한다.",
            notes = "로그아웃 버튼을 눌렀을 때 수행하는 기능",
            response = Response.class)
    public Object logout(HttpServletRequest req, HttpServletResponse res) {

        Cookie refreshToken = cookieUtil.getCookie(req, JwtUtil.REFRESH_TOKEN_NAME);
        Cookie accessToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);

        redisUtil.deleteData(refreshToken.getValue());
        redisUtil.deleteData("FCM_TOKEN_"+jwtUtil.getUsername(accessToken.getValue()));

        accessToken = new Cookie(JwtUtil.ACCESS_TOKEN_NAME, null);
        accessToken.setMaxAge(0);
        accessToken.setPath("/");

        refreshToken = new Cookie(JwtUtil.REFRESH_TOKEN_NAME, null);
        refreshToken.setMaxAge(0);
        refreshToken.setPath("/");

        res.addCookie(accessToken);
        res.addCookie(refreshToken);

        if(res==null){
            return new ResponseEntity<>(new Response("error", "이미 로그아웃된 사용자 입니다.", null), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(new Response("success", "로그아웃 성공", null),HttpStatus.OK);
        }
    }
}
