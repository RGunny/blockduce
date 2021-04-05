package com.special.blockduce.candidate;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.member.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitServie initServie;

    /**
     * spring bean이 다 올라온 후 호출
     */
//    @PostConstruct
//    public void init() {
//        initServie.dbInit();
//    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitServie {

        private final EntityManager em;

        public void dbInit() {
//            Candidate candidate0 = createCandidate("관리자", 0, "관리자", "관리자", "관리자");
//            em.persist(candidate0);
            Account account = createAccount("0x9DFD19acAc0c523D19F9B50B4640a0dD74E092E6", Double.MAX_VALUE, Double.MAX_VALUE, "91b40449775898b8c31c8cb914f5408bc4e2a619cab888fcf1b0f823b8905ffd");
            Candidate candidate = createCandidate("관리자", 0, "관리자", "관리자", "관리자", account);
            em.persist(candidate);

            Account account1 = createAccount("0xB9Eb08b79965E9e78754c9c3686E0028E2C67CDE", 0.0, 0.0, "0xaa5f2f51b6cbd58383e8cdcd9f1382d7f4e061b06c092efff1801909f64e5a1e");

            Candidate candidate1 = createCandidate("황호연", 10, "가족기획", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_11.png", "안녕하세요.", account1);
            em.persist(candidate1);
            Candidate candidate2 = createCandidate("황영준", 28, "싸피", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_12.png", "안녕하세요", account1);
            em.persist(candidate2);
            Candidate candidate3 = createCandidate("최주아", 18, "YG", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_1.png", "안녕하세요", account1);
            em.persist(candidate3);
            Candidate candidate4 = createCandidate("류건희", 21, "JYP", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_2.png", "안녕하세요", account1);
            em.persist(candidate4);
            Candidate candidate5 = createCandidate("박상우", 19, "SM", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_3.png", "안녕하세요", account1);
            em.persist(candidate5);
            Candidate candidate6 = createCandidate("이아영", 15, "CUBE", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_4.png", "안녕하세요", account1);
            em.persist(candidate6);
            Candidate candidate7 = createCandidate("윤은철", 24, "위", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_5.png", "안녕하세요", account1);
            em.persist(candidate7);
            Candidate candidate8 = createCandidate("박성준", 19, "마루기획", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_6.png", "안녕하세요", account1);
            em.persist(candidate8);
            Candidate candidate9 = createCandidate("서범석", 22, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_7.png", "안녕하세요", account1);
            em.persist(candidate9);
            Candidate candidate10 = createCandidate("오현정", 18, "플레디스", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_8.png", "안녕하세요", account1);
            em.persist(candidate10);
            Candidate candidate11 = createCandidate("이수민", 20, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_9.png", "안녕하세요", account1);
            em.persist(candidate11);
            Candidate candidate12 = createCandidate("김예슬", 17, "울림", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_10.png", "안녕하세요", account1);
            em.persist(candidate12);

        }

        private Candidate createCandidate(String name, int age, String agency, String img, String intro, Account account) {
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setAge(age);
            candidate.setAgency(agency);
            candidate.setImg(img);
            candidate.setIntro(intro);
            candidate.setAccount(account);
            return candidate;
        }

        private Account createAccount(String memberAccount, Double dbc, Double eth, String privateKey) {
            Account account = new Account();
            account.setAccount(memberAccount);
            account.setDbc(dbc);
            account.setEth(eth);
            account.setKey(privateKey);
            return account;
        }

    }

}

