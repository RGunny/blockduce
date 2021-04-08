package com.special.blockduce.transaction.service;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.candidate.repository.CandidateRepository;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.repository.AccountRepository;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.DBCStatus;
import com.special.blockduce.transaction.domain.ETH;
import com.special.blockduce.transaction.dto.AccountDto;
import com.special.blockduce.transaction.dto.AccountInfoDto;
import com.special.blockduce.transaction.dto.DbcEthDto;
import com.special.blockduce.transaction.repository.DbcRepository;
import com.special.blockduce.transaction.repository.EthRepository;
import com.special.blockduce.utils.SaltUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
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
import org.web3j.tx.RawTransactionManager;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final MemberRepository memberRepository;
    private final CandidateRepository candidateRepository;
    private final DbcRepository dbcRepository;
    private final EthRepository ethRepository;
    private final AccountRepository accountRepository;

    //dbc 트렌젝션을 만든다  ------ dbc(보낸사람 dbc는 - 받은사람 dbc는 +) *eth도 동일
    @Transactional
    public void create(DbcEthDto form) {

        Member sender = findMemberByid(form.getSenderId());
        Candidate receiver = findCandidateByid(form.getReceiverId());

        DbcEthDto senderAccount =  DbcEthDto.builder().
                   senderAccount(sender.getAccount().getAccount()).
                   build();

        DbcEthDto receiverAccount =  DbcEthDto.builder().
                receiverAccount(receiver.getAccount().getAccount()).
                build();
        /**
         * 후보자에게 투표했을경우
         * 회원의 dbc를 후보자에게 전송
         * dbc테이블에서 + 맴버의 아이디가 senderId에 있는게 투표 트렌젝션
         * 구동확인
         */

        if(form.getIsDbcEth()==1) {
            DBC dbc = DBC.builder().
                    senderId(form.getSenderId()).
                    receiverId(form.getReceiverId()).
                    senderAccount(senderAccount.getSenderAccount()).
                    receiverAccount(receiverAccount.getReceiverAccount()).
                    blockHash(form.getBlockHash()).
                    value(form.getValue()).  //보낸양 eth or dbc
                    transactionFee(form.getTransactionFee()).  //만들때 사용한 가스비
                    gasUsed(form.getGasUsed()).
                    localDateTime(LocalDateTime.now()).
                    blockNumber(form.getBlockNumber()).
                    transactionHash(form.getTransactionHash()).
                    status(DBCStatus.ELECTION).
                    build();

            dbcRepository.save(dbc);

            //맴버 찾아서 dto처리
            Member mem = new Member();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Member member = memberRepository.findMemberByid(form.getSenderId()).orElse(mem);
            if(member.getId() != null){ //계정이 있을 경우
                member.getAccount().updateDbc(member.getAccount().getDbc() - form.getValue());
            }

            Candidate can = new Candidate();
            Candidate candi = candidateRepository.findCandidateByid(form.getReceiverId()).orElse(can);
            if(candi.getId()!=null){ //계정이 있을 경우

                candi.getAccount().updateDbc(candi.getAccount().getDbc()+ form.getValue());

            }
        }

        /**
         * 굿즈 구매에 eth 를 사용했을 경우
         * 회원의 eth를 관리자에게 전송
         * eth 테이블에서 + 관라자의 아이디가 receiverId에 있으면 굿즈 구매 트렌젝션
         * 구동확인
         */


        else if(form.getIsDbcEth()==0){
            ETH eth = ETH.builder().
                    senderId(form.getSenderId()).
                    receiverId(form.getReceiverId()).

                    senderAccount(senderAccount.getSenderAccount()).
                    receiverAccount(receiverAccount.getReceiverAccount()).

                    blockHash(form.getBlockHash()).
                    value(form.getValue()).
                    transactionFee(form.getTransactionFee()).
                    gasUsed(form.getGasUsed()).
                    localDateTime(LocalDateTime.now()).
                    blockNumber(form.getBlockNumber()).
                    transactionHash(form.getTransactionHash()).
                    build();

            ethRepository.save(eth);

            //맴버 찾아서 dto처리
            Member mem = new Member();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...

            Member member = memberRepository.findMemberByid(form.getSenderId()).orElse(mem);
            if(member.getId()!=null){ //계정이 있을 경우
                member.getAccount().updateEth(member.getAccount().getEth() - form.getValue());
            }

            Candidate can = new Candidate();
            Candidate candi = candidateRepository.findCandidateByid(form.getReceiverId()).orElse(can);
            if(candi.getId()!=null){ //계정이 있을 경우
                candi.getAccount().updateEth(candi.getAccount().getEth()+ form.getValue());

            }

        }
    }

    // server receiver의 id로 각 member와 candidate를 찯아온다 (account를 얻기위해해)
   @Transactional

    public Member findMemberByid(Long id) {

       //맴버 찾아서 dto처리
       Member mem = new Member();
       // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
       Member member = memberRepository.findMemberByid(id).orElse(mem);

       if(member.getId()!=null){ //계정이 있을 경우
           return member;
       }
        return mem;  //계정이 없을 경우 예외처리 or 빈객체
    }

    @Transactional
    public Candidate findCandidateByid(Long id) {

        //맴버 찾아서 dto처리
        Candidate candi = new Candidate();
        // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
        Candidate candidate = candidateRepository.findCandidateByid(id).orElse(candi);

        if(candidate.getId()!=null){ //계정이 있을 경우
            return candidate;

        }
        return candi;  //계정이 없을 경우 예외처리 or 빈객체

    }

    public int countDbcTransactionByMember(Long memberId) {
        Member member = findMemberByid(memberId);
        return dbcRepository.countByMember(member.getId(),DBCStatus.ELECTION);

    }

    public String findKeyByid(Long memberId) {

        Optional<Member> member = memberRepository.findMemberByid(memberId);

        String key ="키키키키키";
        if(member.isPresent()){ //계정이 있을 경우
            key =  member.get().getAccount().getKey();
        }
        return key;
    }


    public Double countTotalSendDbcById(Long memberId) {
        return dbcRepository.countTotalSendDbcById(memberId, DBCStatus.ELECTION);
    }

    public Integer receiveDbcTransactionsById(Long memberId) {
        return dbcRepository.receiveDbcTransactionsById(memberId);
    }



    @Transactional
    public void createRewardDbc(DbcEthDto form) {

        Candidate sender = findCandidateByid(form.getSenderId());  //여기선 보내는 사람 후보자테이블(관리자) 받는 사람 맴버테이블(회원)
        Member receiver = findMemberByid(form.getReceiverId());

        DbcEthDto senderAccount =  DbcEthDto.builder().
                senderAccount(sender.getAccount().getAccount()).
                build();

        DbcEthDto receiverAccount =  DbcEthDto.builder().
                receiverAccount(receiver.getAccount().getAccount()).
                build();

        /**
         * qr코드 영상 시청, 출책 등의 보상으로 dbc를 보상받은경우
         * 관리자의 dbc를 회원에게 전송
         * dbc 테이블에서 + 관라자의 아이디가 senderId에 있으면 dbc 보상 트렌젝션
         * 동작 확인
         */
        System.out.println("이즈 디비씨"+form.getIsDbcEth());
        System.out.println("리시버아이디"+form.getReceiverId());
        System.out.println("센더아이디"+form.getSenderId());
        System.out.println("리시버아이디"+receiver.getId());
        System.out.println("센더아이디"+sender.getId());

        if(form.getIsDbcEth()==1) {
            DBC dbc = DBC.builder().
                    receiverId(sender.getId()).
                    senderId(receiver.getId()).
                    senderAccount(senderAccount.getSenderAccount()).
                    receiverAccount(receiverAccount.getReceiverAccount()).
                    blockHash(form.getBlockHash()).
                    value(form.getValue()).  //보낸양 eth or dbc
                    transactionFee(form.getTransactionFee()).  //만들때 사용한 가스비
                    gasUsed(form.getGasUsed()).
                    localDateTime(LocalDateTime.now()).
                    blockNumber(form.getBlockNumber()).
                    transactionHash(form.getTransactionHash()).
                    status(DBCStatus.REWARD).
                    build();

//            DBC updateRewardDbc = dbc.updateRewardDbc(dbc, form.getReceiverId(), form.getSenderId());
            dbcRepository.save(dbc);


            System.out.println("form.getSenderId() : 관리자아이디:" +form.getSenderId());
            System.out.println("form.getmemberId() : 보상받는사람아이디:" +form.getReceiverId());
            System.out.println("form.getValue()" +form.getValue());

            //맴버 찾아서 dto처리
            Candidate candi = new Candidate();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Candidate s_admin = candidateRepository.findCandidateByid(form.getSenderId()).orElse(candi);

            System.out.println("s_admin : 관리자의 현재dbc:" +s_admin.getAccount().getDbc());

            if(s_admin.getId() != null){ //계정이 있을 경우
                s_admin.getAccount().updateDbc(s_admin.getAccount().getDbc() - form.getValue());
            }

            Member mem2 = new Member();
            Member r_member = memberRepository.findMemberByid(form.getReceiverId()).orElse(mem2);

            System.out.println("r_member.getAccount().getAccount()" +r_member.getAccount().getAccount());
            System.out.println("r_member.getId(): " +r_member.getId());
            if(r_member.getId()!=null){ //계정이 있을 경우
                r_member.getAccount().updateDbc(r_member.getAccount().getDbc()+ form.getValue());
            }
        } //완료

        /**
         * 투표에 대한 보상으로 eth를 받는 경우
         * 관리자의 eth를 회원에게 전송
         * eth 테이블에서 + 관라자의 아이디가 senderId에 있으면 eth 보상 트렌젝션
         * 동작확인
         */

        else if(form.getIsDbcEth()==0){

            System.out.println("else if(form.getIsDbcEth()==0) 여기 들어왔나?");
            ETH eth = ETH.builder().
                    receiverId(form.getReceiverId()).
                    senderId(form.getSenderId()).
                    senderAccount(senderAccount.getSenderAccount()).
                    receiverAccount(receiverAccount.getReceiverAccount()).
                    blockHash(form.getBlockHash()).
                    value(form.getValue()).
                    transactionFee(form.getTransactionFee()).
                    gasUsed(form.getGasUsed()).
                    localDateTime(LocalDateTime.now()).
                    blockNumber(form.getBlockNumber()).
                    transactionHash(form.getTransactionHash()).
                    build();

            ETH updateRewardEth = eth.updateRewardEth(eth, form.getReceiverId(), form.getSenderId());

            ethRepository.save(updateRewardEth);

            //맴버 찾아서 dto처리
            Member mem = new Member();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Member member = memberRepository.findMemberByid(form.getReceiverId()).orElse(mem);
            if(member.getId()!=null){ //계정이 있을 경우
                member.getAccount().updateEth(member.getAccount().getEth() + form.getValue());
            }

            //맴버 찾아서 dto처리
            Candidate candi = new Candidate();
            // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
            Candidate s_admin = candidateRepository.findCandidateByid(form.getSenderId()).orElse(candi);

            if(s_admin.getId() != null){ //계정이 있을 경우
                s_admin.getAccount().updateEth(s_admin.getAccount().getEth() - form.getValue());
            }
        }
    }

    public Double countTotalReceiveDbcById(Long memberId) {

        return dbcRepository.countTotalReceiveDbcById(memberId);
    }

    public Integer receiveEthTransactionsById(Long memberId) {
        return ethRepository.receiveEthTransactionsById(memberId);
    }

    public Double countTotalReceiveEthById(Long memberId) {
        return ethRepository.countTotalReceiveEthById(memberId);
    }

    @Transactional
    public String createAccount(AccountDto form,Long memberId) {

        String salt = SaltUtil.genSalt();
        //맴버 아이디로 맴버 찾아와서 맴버.account로 변경하지
        Member mem = new Member();
        // 아이디 찾아오면 member로 넣고 못찾으면 ismem false로 바꿔서 넣고...
        Member member = memberRepository.findMemberByid(memberId).orElse(mem);

        //어카운트 생성 시 eth  0.0999 지급
        ethReward(memberId,333333333.0);
        Double ethInit = 333333333.0*0.0000000003;

        if(member.getId() != null){ //계정이 있을 경우
//            Account account = Account.builder().
//                    key(form.getKey()).
//                    account(form.getAccount()).
//                    dbc(form.getDbc()).
//                    eth(form.getEth()).
//                    build();
            member.getAccount().updateAccount(form.getAccount());
            member.getAccount().updateDbc(form.getDbc());
            member.getAccount().updateKey(form.getKey());
            member.getAccount().updateEth(ethInit);
            return "지갑 생성 완료";
        }
    return "지갑 생성 오류";
    }

    public AccountInfoDto accountInfo(Long memberId) {
        Member member = findMemberByid(memberId);


        AccountInfoDto accountinfodto = AccountInfoDto.builder().
                dbcTransactions(dbcRepository.countByMember(member.getId(), DBCStatus.ELECTION)).
                totalDbc(dbcRepository.countTotalSendDbcById(member.getId(), DBCStatus.ELECTION)).
                receiveTransactionsDbc(dbcRepository.receiveDbcTransactionsById(member.getId())).
                receiveDbc(dbcRepository.countTotalReceiveDbcById(member.getId())).
                receivedEthTransactions(ethRepository.receiveEthTransactionsById(member.getId())).
                receivedEth(ethRepository.countTotalReceiveEthById(member.getId())).build();
        return accountinfodto;
    }

    /**
     * QR 코드를 인증한 유저에 대해 DBC 토큰 보상 지급하는 메서드
     *  reward : 100,000 DBC 지급
     *
     * --> userId로 해당 유저(Member)를 찾아옴
     * --> 해당 유저의
     */
    @Transactional
    public boolean isRewarded(Long userId, DBCStatus dStatus) throws IOException {

        Member member = findMemberByid(userId);

        LocalDate today = LocalDate.now();

        Optional<DBC> isRewarded = dbcRepository.isRewarded(member.getId(), dStatus, today.getMonthValue(), today.getDayOfMonth());


        System.out.println("today.getMonthValue() = " + today.getMonthValue());
        System.out.println("today.getDayOfMonth() = " + today.getDayOfMonth());

        if(isRewarded.isPresent()){
            return true;
        }else{

            long value = 10000000000L;
            Web3j web3 = Web3j.build(new HttpService(
                    "https://ropsten.infura.io/v3/b04025a46bb245b3bdb7c350a938dbe5"));
            String privetKey = "91b40449775898b8c31c8cb914f5408bc4e2a619cab888fcf1b0f823b8905ffd";
            Credentials credentials = Credentials.create(privetKey);
            RawTransactionManager manager = new RawTransactionManager(web3, credentials);
            String toAddress = member.getAccount().getAccount(); // 여기만 findId로 account 가져와서 넣어주세요
            String contractAddress = "0x9864bb32e02b1fae9eb875f7b169c5400b15efec";
            BigInteger sum = BigInteger.valueOf(value); // amount you want to send 100000DBC
            String data = encodeTransferData(toAddress, sum);
            BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
            BigInteger gasLimit = BigInteger.valueOf(120000); // set gas limit here
            EthSendTransaction transaction = manager.sendTransaction(gasPrice, gasLimit, contractAddress, data, null);

            DbcEthDto DbcTransactionInfo = DbcEthDto.builder().
                    senderId(1L).
                    receiverId(userId).
                    senderAccount(credentials.getAddress()).
                    receiverAccount(toAddress).
                    value(100000L).
                    localDateTime(LocalDateTime.now()).
                    transactionHash(transaction.getTransactionHash()).
                    isDbcEth(1L).
                    build();

            System.out.println(DbcTransactionInfo.getValue());
            createRewardDbc(DbcTransactionInfo);
            return false;
        }
    }

    public static String encodeTransferData(String toAddress, BigInteger sum) {
        Function function = new Function(
                "transfer",  // function we're calling
                Arrays.asList(new Address(toAddress), new Uint256(sum)),  // Parameters to pass as Solidity Types
                Arrays.asList(new org.web3j.abi.TypeReference<Bool>() {}));
        return FunctionEncoder.encode(function);
    }

    public String findAccountByid(Long memberId) {
        Optional<Member> member = memberRepository.findMemberByid(memberId);

        String account ="어카운트 못찾았어요 지갑먼저 만들어주세요";
        if(member.isPresent()){ //계정이 있을 경우
            account =  member.get().getAccount().getAccount();
        }
        return account;
    }

    @Transactional
    public void createEthTransaction(DbcEthDto EthTransactionInfo) {
        System.out.println("createEthTransaction 들어왔어");
        createRewardDbc(EthTransactionInfo);
    }

    public String ethReward(Long memberId, Double dbcvelue) {
        String Account = findAccountByid(memberId);
        double velue = 0.0000000003*dbcvelue;

        Web3j web3 = Web3j.build(new HttpService(
                "https://ropsten.infura.io/v3/b04025a46bb245b3bdb7c350a938dbe5"));

        try{
            String privetKey = "91b40449775898b8c31c8cb914f5408bc4e2a619cab888fcf1b0f823b8905ffd";
            Credentials credentials = Credentials.create(privetKey);
            System.out.println("recipientAddress: "+Account);

            System.out.println(credentials.getAddress());
            EthGetTransactionCount ethGetTransactionCount = web3
                    .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // Recipient address
            String recipientAddress = Account;
            // Value to transfer (in wei)

            String tmp = Double.toString(velue);
            String amountToBeSent= tmp; // 60원
            BigInteger value = Convert.toWei(amountToBeSent, Convert.Unit.ETHER).toBigInteger();

            // Gas Parameter
            BigInteger gasLimit = BigInteger.valueOf(21000);
            BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();

            // Prepare the rawTransaction
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit,
                    recipientAddress, value);

            // Sign the transaction
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            // Send transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
            String transactionHash = ethSendTransaction.getTransactionHash();

            System.out.println("transactionHash: " + transactionHash);
            if(transactionHash == null){
                return "사용자가 많아 처리가 지연되고 있습니다. 잠시후 이용해주세요";
            }
            // Wait for transaction to be mined
            Optional<TransactionReceipt> transactionReceipt = null;
            do {
                EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash)
                        .send();
                transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
                Thread.sleep(3000); // Wait for 3 sec
            } while (!transactionReceipt.isPresent());

            System.out.println("Transaction " + transactionHash + " was mined in block # "
                    + transactionReceipt.get().getBlockNumber());
            System.out.println("블록해시 : "
                    + transactionReceipt.get().getBlockHash());
            System.out.println("가스유즈드 : "
                    + transactionReceipt.get().getGasUsed());
            System.out.println("받는사람 주소 : "
                    + Account);
            System.out.println("주는사람 주소(관리자) : "
                    + credentials.getAddress());
            System.out.println("트렌젝션피 : "
                    + transactionReceipt.get().getCumulativeGasUsed());
            System.out.println("트렌젝션해시 : "
                    + transactionReceipt.get().getTransactionHash());
            System.out.println("보낸이더 : "
                    + value);
            System.out.println("보낸사람 아이디 : 후보자테이블 1번  보상 받는 맴버 아이디: "
                    + memberId);
            System.out.println("value: "
                    + (0.0000000003*dbcvelue));

            DbcEthDto EthTransactionInfo = DbcEthDto.builder().
                    senderId(1L).
                    receiverId(memberId).
                    senderAccount(credentials.getAddress()).
                    receiverAccount(Account).
                    blockHash(transactionReceipt.get().getBlockHash()).
                    value((long) (0.0000000003*dbcvelue)).
                    transactionFee(transactionReceipt.get().getCumulativeGasUsed().longValue()).
                    gasUsed(transactionReceipt.get().getGasUsed().longValue()).
                    localDateTime(LocalDateTime.now()).
                    blockNumber(transactionReceipt.get().getBlockNumber().longValue()).
                    transactionHash(transactionReceipt.get().getTransactionHash()).
                    isDbcEth(0L).
                    build();
            System.out.println("EthTransactionInfo의 isdbceth는?"+EthTransactionInfo.getIsDbcEth());
            System.out.println("EthTransactionInfo의 isdbceth는?"+EthTransactionInfo.getIsDbcEth());
            createEthTransaction(EthTransactionInfo);

            return "eth 보상 지급완료";
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }


//
//    public HttpStatus countTotalDbcById(Long memberId) {
//        return dbcRepository.countById(memberId);
//    }

}
