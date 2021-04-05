package com.special.blockduce.candidate.controller;


import com.special.blockduce.candidate.dto.CandidateDto;
import com.special.blockduce.candidate.service.CandidateService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    /**
     * 후보자 생성 db에 넣어주기
     * 관리자 계정도 후보자 테이블에 생성됩니다. eth 보상 시 eth 트렌젝션 발생시켜서 기록하기 위해서
     * */
    @PostMapping("/candidate/join")
    public ResponseEntity<String> join(@RequestBody CandidateDto form){

        candidateService.join(form);
        return new ResponseEntity<>("success", HttpStatus.OK);
        //ResponseEntity로 성공 메세지 전달 가능
    }

    /**
     * 후보자 아이디를 통한 조회
     * */
    @GetMapping("/candidates")
    public Result<CandidateDto> findAll() {
        return new Result(candidateService.findAll());
    }

}
