package com.special.blockduce.member.controller;

import com.special.blockduce.Response;
import com.special.blockduce.image.service.ImageService;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.request.*;
import com.special.blockduce.member.domain.response.LoginMemberResponse;
import com.special.blockduce.member.service.AuthService;
import com.special.blockduce.utils.CookieUtil;
import com.special.blockduce.utils.JwtUtil;
import com.special.blockduce.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Value("${file.path}")
    String localFilePath;

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;
    private final ImageService imageService;


    @PostMapping("/signup")
    @ApiOperation(
            value = "회원가입",
            notes = "회원가입시 SignupMemberRequest의 데이터 타입으로 입력받아 가입을 진행한다.",
            response = Response.class
    )
    public Object signUpMember(@RequestPart(required = false) MultipartFile image, SignupMemberRequest signupMemberRequest) {
        ResponseEntity<Response> responseEntity = null;

        if (authService.existsByEmail(signupMemberRequest.getEmail())){
            final Response result = new Response("success","중복된 회원 이메일 발견", "duplicated email exception");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        

        //유저 대표 이미지 저장
        if(image != null){
            try {
                String imgName = imageService.createImage(image);
                signupMemberRequest.setProfileImageUrl(imgName);
            }
            catch (IOException e){
                final Response result = new Response("success","회원가입 이미지 저장 중 오류 발생", e.getMessage());
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        }//파일 저장 끝

        try{
            authService.signUpMember(signupMemberRequest);
            final Response result = new Response("success","회원가입 성공",null);
            responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            final Response result = new Response("error","회원가입 중 오류 발생",e.getMessage());
            responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @PostMapping("/login")
    @ApiOperation(
            value = "로그인",
            notes = "post로 LoginMemberRequest 형태의 데이터를 받아서 로그인 처리와 토큰을 발급해줍니다.",
            response = Response.class
    )
    public Object login(@Valid @RequestBody LoginMemberRequest user, HttpServletRequest request,HttpServletResponse response) {
        try {
            final Member member = authService.loginMember(user.getUserEmail(), user.getPassword());
            final String token = jwtUtil.generateToken(member);
            final String refreshJwt = jwtUtil.generateRefreshToken(member);

            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);

            redisUtil.setDataExpire(refreshJwt, member.getName(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

            response.addCookie(accessToken);
            response.addCookie(refreshToken);

            return new ResponseEntity<>(new Response("success","로그인에 성공했습니다.", LoginMemberResponse.of(member,accessToken.getValue())),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response("error","로그인이 실패하였습니다. 아이디 / 비밀번호 확인해주세요",e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verify")
    @ApiOperation(
            value = "이메일 인증 메일 발송",
            notes = "회원가입 후 이메일 인증 메일을 발송한다",
            response = Response.class
    )
    public Response verify(@RequestBody VerifyEmailRequest verifyEmailRequest, HttpServletRequest req, HttpServletResponse res) {
        Response response;
        try {
            Member member = authService.findMemberByName(verifyEmailRequest.getUsername());
            authService.sendVerificationMail(member);
            response = new Response("success", "성공적으로 인증메일을 보냈습니다.", null);
        } catch (Exception exception) {
            response = new Response("error", "인증메일을 보내는데 문제가 발생했습니다.", exception);
        }
        return response;
    }

    @GetMapping("/verify/{key}")
    @ApiOperation(
            value = "이메일 인증 확인",
            notes = "이메일 로그인 후, 발송받은 이메일의 내용에서 url을 클릭하면 이메일 인증이 완료된다.",
            response = Response.class
    )
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
    @ApiOperation(value = "비밀번호 변경",
            notes = "비밀번호 변경 확인 메일을 전송한다.",
            response = Response.class)
    public Response requestChangePassword(@RequestBody SendChangePasswordRequest sendChangePasswordRequest) {
        Response response;
        try {
            Member member = authService.findMemberByName(sendChangePasswordRequest.getName());
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
    @ApiOperation(value = "비밀번호 변경 진행",
            notes = "비밀번호 변경 확인 링크를 클릭하고 비밀번호를 변경을 진행한다.",
            response = Response.class)
    public Response changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Response response;
        try {
            Member member = authService.findMemberByName(changePasswordRequest.getEmail());
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
        redisUtil.deleteData("FCM_TOKEN_" + jwtUtil.getUsername(accessToken.getValue()));

        accessToken = new Cookie(JwtUtil.ACCESS_TOKEN_NAME, null);
        accessToken.setMaxAge(0);
        accessToken.setPath("/");

        refreshToken = new Cookie(JwtUtil.REFRESH_TOKEN_NAME, null);
        refreshToken.setMaxAge(0);
        refreshToken.setPath("/");

        res.addCookie(accessToken);
        res.addCookie(refreshToken);

        if (res == null) {
            return new ResponseEntity<>(new Response("error", "이미 로그아웃된 사용자 입니다.", null), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new Response("success", "로그아웃 성공", null), HttpStatus.OK);
        }
    }
}
