package com.special.blockduce.transaction.controller;

import com.special.blockduce.member.dto.MemberForm;
import com.special.blockduce.member.service.MemberService;
import com.special.blockduce.transaction.dto.DbcEthDto;
import com.special.blockduce.transaction.dto.DbcEthDto;
import com.special.blockduce.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * DBC,ETH 트렌젝션 생성
     * */
    @PostMapping("/election/dbc")
    public ResponseEntity<String> dbcTransaction(@RequestBody DbcEthDto form){

        transactionService.create(form);

        return new ResponseEntity<>("전송완료", HttpStatus.OK);
    }

}
