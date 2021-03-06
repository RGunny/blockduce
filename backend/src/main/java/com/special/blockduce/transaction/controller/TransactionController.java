package com.special.blockduce.transaction.controller;



import com.special.blockduce.transaction.domain.DBCStatus;
import com.special.blockduce.transaction.domain.ETH;
import com.special.blockduce.transaction.dto.AccountDto;
import com.special.blockduce.transaction.dto.AccountInfoDto;
import com.special.blockduce.transaction.dto.DbcEthDto;
import com.special.blockduce.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * DBC,ETH 트렌젝션 생성 (회원이 후보자에게 투표하는 경우)
     * */
    @PostMapping("/election/dbc")
    public ResponseEntity<String> createDbcTransaction(@RequestBody DbcEthDto form){

        transactionService.create(form);

        return new ResponseEntity<>("전송완료", HttpStatus.OK);
    }

    /**
     * DBC,ETH 트렌젝션 생성 (관리자 계정으로 회원에게 dbc나 eth를 보내주는경우(출책))
     * 이 경우 받는 사람이 -> MEMBER 테이블의 회원
     * 주는 사람은 -> CANDIDATE 테이블의 관리자 계정이다
     * createDbcTransaction에서 처리하면 직관성이 너무 떨어져서 분리함
     * */
    @PostMapping("/election/rewardDbcEth")
    public ResponseEntity<String> createRewardDbcTransaction(@RequestBody DbcEthDto form){

        transactionService.createRewardDbc(form);
        return new ResponseEntity<>("전송완료", HttpStatus.OK);

    }


    /**
     * 어카운트 페이지 들어갔을때 트렌젝션 정보 한번에 조회하기
     */
    @GetMapping("/election/accountInfo/{memberId}")
    public ResponseEntity<AccountInfoDto> accountInfo(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.accountInfo(memberId), HttpStatus.OK);

    }

    /**
     * QR 찍었을때 DBC 서버에서 보내주는 로직
     * election/receiveDBC/{35}
     * <p>
     * 100,000
     * <p>
     * response로 날짜 검사해서 userId 중복이면 response = false
     * <p>
     * 날짜 비워있으면 response = true 하고 account Table도 refresh
     */
    @GetMapping("/election/isrewarded/{userId}/{status}")
    public ResponseEntity<String> isRewarded(@PathVariable("userId") Long userId, @PathVariable("status") DBCStatus status) throws IOException {

        if (transactionService.isRewarded(userId, status)) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
    }

    /**
     * 해당 맴버의 총 투표횟수 조회(dbc 트랜젝션 발생횟수)
     * dbc테이블에서 + 맴버의 아이디가 senderId에 있는게 투표 트렌젝션
     * */
    //dbc 테이블에서 맴버 id로 count 해서 조회
    @GetMapping("/election/transactions-dbc/{memberId}")
    public ResponseEntity<Integer> dbcTransactions(@PathVariable("memberId") Long memberId) {

        return new ResponseEntity<>(transactionService.countDbcTransactionByMember(memberId), HttpStatus.OK);
    }


    /**
     * 투표한 양 조회(투표에 사용한 총 dbc 사용량)
     * */
    @GetMapping("/election/total-dbc/{memberId}")
    public ResponseEntity<Double> totalDbc(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<Double>(transactionService.countTotalSendDbcById(memberId), HttpStatus.OK);

    }


    /**
     * dbc받은 횟수(투표에 사용할 dbc를 받은 횟수 -qr코드, 사이트 방문을 통해 지급)
     *
     * dbc 테이블에서 senderId -> dbc주는 관리자 계정    receiverId -> memberId  인 것을 카운트해서 return
     * */

    @GetMapping("/election/receive-transactions-dbc/{memberId}")
    public ResponseEntity<Integer> receivedDbcTransactions(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.receiveDbcTransactionsById(memberId), HttpStatus.OK);
    }


    /**
     * dbc받은 양(투표에 사용할 dbc 받은 양)
     *
     * dbc 테이블에서 candidateId 가 memberId 인 것들의 value 합
     * */
    @GetMapping("/election/received-dbc/{memberId}")
    public ResponseEntity<Double> receivedDbc(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.countTotalReceiveDbcById(memberId), HttpStatus.OK);
    }


    /**
     * eth 받은 횟수(투표에 사용할 dbc 받은 양)
     *
     * eth 테이블에서 senderId -> eth 관리자 계정    receiverId -> memberId  인 것을 카운트해서 return
     * */

    @GetMapping("/election/receive-transactions-eth/{memberId}")
    public ResponseEntity<Integer> receivedEthTransactions(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.receiveEthTransactionsById(memberId), HttpStatus.OK);
    }

    /**
     * eth 받은 양(투표에 사용할 dbc 받은 양)
     *
     * eth 테이블에서 candidateId 가 memberId 인 것들의 value 합
     * */

    @GetMapping("/election/received-eth/{memberId}")
    public ResponseEntity<Double> receivedEth(@PathVariable("memberId") Long memberId){


        return new ResponseEntity<>(transactionService.countTotalReceiveEthById(memberId), HttpStatus.OK);
    }

    /**
     * id 받고 키 주기
     * */

    @GetMapping("/election/key/{memberId}")
    public ResponseEntity<String> findKey(@PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.findKeyByid(memberId), HttpStatus.OK);
    }

    /**
     * 지갑 생성 눌렀을 경우 account key dbc(0) eth(0) 으로 넣어주기
     */
    @PutMapping("/election/createAccount/{memberId}")
    public ResponseEntity<String> createAccount(@RequestBody AccountDto form, @PathVariable("memberId") Long memberId){

        return new ResponseEntity<>(transactionService.createAccount(form,memberId), HttpStatus.OK);
    }


    /**
     * 투표한 맴버에게 eth 제공
     * */

    @GetMapping("/election/EthReward/{memberId}/{dbcvelue}")
    public ResponseEntity<String> ethReward(@PathVariable("memberId") Long memberId,@PathVariable("dbcvelue") Double dbcvelue){

        String result = transactionService.ethReward(memberId,dbcvelue);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
