package com.special.blockduce.transaction.controller;

import com.special.blockduce.candidate.service.CalendarService;
import com.special.blockduce.transaction.dto.DbcDatesDto;
import com.special.blockduce.transaction.dto.DbcResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


    /**
     * 1번 그림
     *
     *  request : userId, year, month
     *  ex) api/election/content/{userid}/{date}
     *  userId : 35
     *  date : 2021/04
     *  받고 싶은 데이터 : month의 날짜별 true false
     *      -> 해당 달에 해당 유저가 투표한 날에 대해
     *      -> userId : sender_id
     *
     * @return
     */
    @GetMapping("/elections/content/{userId}/{year}/{month}")
    @ApiOperation(
            value = "유저가 투표한 날을 캘린더에 표시",
            notes = "캘린더 해당 월에 해당 유저가 투표할 날에 대한 표시"
    )
    public DbcDatesDto getElections(@PathVariable Long userId, @PathVariable int year, @PathVariable int month){


        return calendarService.getElections(userId, year, month);
    }



    /**
     * 2번 그림
     *
     *  보내주는 데이터 : userId, year, month, day
     *  ex) api/election/detail/{userid}/{date}
     *  userId : 35
     *  date : 2021/04/02
     *  받고 싶은 데이터 : day 에 있었던 trasaction 데이터
     *  block_number, block_hash, candidate_img, candidate_name, agency, timeStamp ,DBC 양 ,total DBC 양
     *      -> receiver_id : cadndidate table - candidate_id
     *      -> sender_id : member table - member_id 
     *      -> DBC 양 : dbc table - value
     *      -> total DBC 양 : dbc table - 해달 날의 value 합
     * @return
     */
    @GetMapping("/elections/{userId}/{month}/{day}")
    @ApiOperation(
            value = "선택한 일에 해당 유저의 투표 정보",
            notes = "선택한 일에 대한 해당 유저의 모든 투표(transaction) 정보 표시. " +
                    " -> receiver_id : cadndidate table - candidate_id" +
                    " -> sender_id : member table - member_id" +
                    " -> DBC 양 : dbc table - value" +
                    " -> total DBC 양 : dbc table - 해달 날의 value 합"
    )
    public List<DbcResponseDto> getElection(@PathVariable Long userId, @PathVariable int month, @PathVariable int day) {

        return calendarService.getElection(userId, month, day);
    }
}
