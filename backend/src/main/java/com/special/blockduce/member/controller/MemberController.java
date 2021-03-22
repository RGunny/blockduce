package com.special.blockduce.member.controller;

import com.special.blockduce.member.dto.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.special.blockduce.member.service.*;

import java.util.HashMap;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/klogin")
    public HashMap<String, String> klogin(@RequestParam String authorize_code) {
        String access_token = memberService.getAccessToken(authorize_code); //인가 코드를 이용해 사용자코드 받아오기
        System.out.println("억세스 토큰 확인: "+access_token);
        HashMap<String, String> userinfo = memberService.getUserInfo(access_token); //사용자 코드를 이용해 유저 프로필 받아오기
        return userinfo;
    }

    /**
     * 맴버 생성 db에 넣어주기
     * */
    @PostMapping("/members/join")  // post - 양식 작성 후 회원가입하기 클릭 시 json으로 받아올거 ->세션에 저장된 member_id + 프로필 양식에 넣은 값
    public ResponseEntity<String> join(@RequestBody MemberForm form){

        memberService.join(form);
        return new ResponseEntity<>("success", HttpStatus.OK); //이건 컨트롤러에서 해당 뷰를 보여주는 것이 아니라 redirect 오른쪽 주소로 url 요청 다시하는거(새로고침)
        //ResponseEntity로 성공 메세지 전달 가능
    }

//    /**
//     * 맴버 우리사이트에 로그인 db에 넣어주기
//     * */
//    @PostMapping("/members/login")  // post - 양식 작성 후 회원가입하기 클릭 시 json으로 받아올거 ->세션에 저장된 member_id + 프로필 양식에 넣은 값
//
//    }
}
