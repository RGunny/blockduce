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
    @GetMapping("/candidate/findAll")
    public Result<CandidateDto> findAll() {
        return new Result(candidateService.findAll());
    }

}
