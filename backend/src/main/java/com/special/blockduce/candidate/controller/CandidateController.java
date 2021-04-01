package com.special.blockduce.candidate.controller;

import com.special.blockduce.candidate.dto.ReadCandidatesResponse;
import com.special.blockduce.candidate.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/candidates")
    @ApiOperation(
            value = "read candidates",
            notes = "투표하기 페이지를 위한 전체 후보자들 조회"
    )
    public Result<ReadCandidatesResponse> findCandidates() {
        return new Result(candidateService.findCandidates());
    }

}
