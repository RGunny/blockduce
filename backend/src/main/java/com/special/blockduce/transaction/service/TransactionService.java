package com.special.blockduce.transaction.service;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.candidate.repository.CandidateRepository;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.ETH;
import com.special.blockduce.transaction.dto.DbcEthDto;
import com.special.blockduce.transaction.repository.DbcRepository;
import com.special.blockduce.transaction.repository.EthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final MemberRepository memberRepository;
    private final CandidateRepository candidateRepository;
    private final DbcRepository dbcRepository;
    private final EthRepository ethRepository;

    //dbc 트렌젝션을 만든다  ------ dbc(보낸사람 dbc는 - 받은사람 dbc는 +) *eth도 동일
    @Transactional
    public void create(DbcEthDto form) {

        DbcEthDto senderaccount = findMemberByid(form.getSenderId());
        DbcEthDto receiveraccount = findCandidateByid(form.getReceiverId());

        if(form.getIsDbcEth()==1) {
            DBC dbc = DBC.builder().
                    senderId(form.getSenderId()).
                    receiverId(form.getReceiverId()).
                    senderAccount(senderaccount.getSenderAccount()).
                    receiverAccount(receiveraccount.getReceiverAccount()).
                    blockHash(form.getBlockHash()).
                    value(form.getValue()).  //보낸양 eth or dbc
                    transactionFee(form.getTransactionFee()).  //만들때 사용한 가스비
                    gasUsed(form.getGasUsed()).
                    timeStamp(form.getTimeStamp()).
                    blockNumber(form.getBlockNumber()).
                    build();

            dbcRepository.save(dbc);

            //맴버 찾아서 dto처리
            Member mem = new Member();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Member member = memberRepository.findMemberByid(form.getSenderId()).orElse(mem);
            System.out.println("찾아온 id: "+member.getId());
            System.out.println("찾아온 dbc: "+member.getAccount().getDbc());
            if(member.getId() != null){ //계정이 있을 경우
                member.getAccount().updateDbc(member.getAccount().getDbc() - form.getValue());
            }

            Candidate can = new Candidate();
            Candidate candi = candidateRepository.findCandidateByid(form.getReceiverId()).orElse(can);
            if(candi.getId()!=null){ //계정이 있을 경우
                candi.updateDbc(candi.getAccount().getDbc()+ form.getValue());
            }
        }

        else if(form.getIsDbcEth()==0){
            ETH eth = ETH.builder().
                    senderId(form.getSenderId()).
                    receiverId(form.getReceiverId()).
                    senderAccount(senderaccount.getSenderAccount()).
                    receiverAccount(receiveraccount.getReceiverAccount()).
                    blockHash(form.getBlockHash()).
                    value(form.getValue()).
                    transactionFee(form.getTransactionFee()).
                    gasUsed(form.getGasUsed()).
                    timeStamp(form.getTimeStamp()).
                    blockNumber(form.getBlockNumber()).
                    build();

            ethRepository.save(eth);

            //맴버 찾아서 dto처리
            Member mem = new Member();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Member member = memberRepository.findMemberByid(form.getId()).orElse(mem);
            if(member.getId()!=null){ //계정이 있을 경우
                member.getAccount().updateEth(member.getAccount().getEth() - form.getValue());
            }
        }
    }

    // server receiver의 id로 각 member와 candidate를 찯아온다 (account를 얻기위해해)
   @Transactional
    public DbcEthDto findMemberByid(Long id) {

        Optional<Member> member = memberRepository.findMemberByid(id);
       System.out.println("member의 어카운트 찾아왔니? :"+member.get().getAccount().getAccount());
       DbcEthDto dbcDto = new DbcEthDto();
        if(member.isPresent()){ //계정이 있을 경우
            dbcDto =  DbcEthDto.builder().
                    senderAccount(member.get().getAccount().getAccount()).
                    build();
        }
        return dbcDto;
    }

    @Transactional
    public DbcEthDto findCandidateByid(Long id) {

        Optional<Candidate> candidate = candidateRepository.findCandidateByid(id);
        System.out.println("candidate 어카운트 찾아왔니? :"+candidate.get().getAccount().getAccount());

        DbcEthDto dbcDto = new DbcEthDto();
        if(candidate.isPresent()){ //계정이 있을 경우
            dbcDto =  DbcEthDto.builder().
                    receiverAccount(candidate.get().getAccount().getAccount()).
                    build();
        }
        return dbcDto;
    }

    public int countTransactionById(Long memberId) {
        return dbcRepository.countById(memberId);
    }

    public String findKeyByid(Long memberId) {

        Optional<Member> member = memberRepository.findMemberByid(memberId);
        System.out.println("member의 키 찾아왔니? :"+member.get().getAccount().getKey());

        String key ="키키키키키";
        if(member.isPresent()){ //계정이 있을 경우
            key =  member.get().getAccount().getKey();
        }
        return key;
    }
//
//    public HttpStatus countTotalDbcById(Long memberId) {
//        return dbcRepository.countById(memberId);
//    }


}
