package com.special.blockduce.member.controller;

import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.dto.MemberForm;
import com.special.blockduce.member.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.special.blockduce.member.service.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    @Autowired
    private JwtService jwtService;

    //인가코드를 통한 프로필로 우리 사이트 회원인지 검사(벡에서) 회원 아니면 회원가입으로 회원이면 억세스 토큰받고 홈으로
    @GetMapping("/members/klogin")
    public MemberForm klogin(@RequestParam String authorize_code) {
        String access_token = memberService.getAccessToken(authorize_code); //인가 코드를 이용해 사용자코드 받아오기
        System.out.println("억세스 토큰 확인: "+access_token);
        ProfileDto userinfo = memberService.getUserInfo(access_token); //사용자 코드를 이용해 유저 프로필 받아오기

        //findById 카카오 고유값을 통해 찾아보자-> id 확인 있으면 jwt토큰 발급해서 dto 넣어주고 -> 홈화면   으로 없으면-> join으로
        MemberForm member = memberService.findByKid(userinfo);
        System.out.println("맴버 케이아이디 :"+member.getKid());
        System.out.println("맴버 이미지 :"+userinfo.getImg());

        //getismem을 통해서 프론트에서 회원이 있는지 없는지 확인
        MemberForm memberResult;

        memberResult = MemberForm.builder().
                kid(member.getKid()).
                email(member.getEmail()).
                name(member.getName()).
                ismem(member.getIsmem()).
                img(userinfo.getImg()).
                build();

        return memberResult;
    }

    /**
     * 맴버 생성 db에 넣어주기
     * */
    @PostMapping("/members/join")  // post - 양식 작성 후 회원가입하기 클릭 시 json으로 받아올거 -> email pw name
    public ResponseEntity<String> join(@RequestBody MemberForm form){
        System.out.println("닉네임: "+form.getNickname());
        System.out.println("인트로: "+form.getIntro());
        System.out.println("이미지: "+form.getImg());
        memberService.join(form);
        return new ResponseEntity<>("success", HttpStatus.OK);
        //ResponseEntity로 성공 메세지 전달 가능
    }

    /**
     * 로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
     * */
    @PostMapping("/members/login")  // post - 양식 작성 후 회원가입하기 클릭 시 json으로 받아올거 ->세션에 저장된 member_id + 프로필 양식에 넣은 값
    public ResponseEntity<MemberForm> login(@RequestBody MemberForm form){
        MemberForm memf;
        System.out.println("로그인시 받아오는거 : "+form.getEmail());

        MemberForm member = memberService.findByEmail(form.getEmail());
        String token = jwtService.create(form);  //jwt토근에는 dto 전달 -> db 건드리는거 아니니까 어짜피
        logger.trace("로그인 토큰정보 : {}", token); //logger에 기록

        memf = MemberForm.builder().
                id(member.getId()).
                token(token).
                build();

        return new ResponseEntity<>(memf, HttpStatus.OK); //이건 컨트롤러에서 해당 뷰를 보여주는 것이 아니라 redirect 오른쪽 주소로 url 요청 다시하는거(새로고침)
    }

    /**
     * 맴버 정보 확인
     * */
    @GetMapping("/members/{memberId}")
    public MemberForm memberInfo(@PathVariable("memberId") Long memberId){
        System.out.println("들어왔니?");
        MemberForm form = new MemberForm();
        MemberForm member = memberService.findById(memberId,form);

        return member;
    }


    /**
     * 해당 memberId에 이더리움 저장 + DBC 저장
     * */
    @PutMapping("/members/{memberId}")
    public MemberForm replaceMember(@PathVariable("memberId") Long memberId,@RequestBody MemberForm form){
        System.out.println("왜안됨?"+form.getEth());
        //변경까지하고 member로 받는다
        MemberForm member = memberService.findById(memberId,form);

        return member;
    }


}
